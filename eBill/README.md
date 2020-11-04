# eBill 2.0

This `eBill 2.0` is **Aboitiz Power**'s pilot project for implementing [Microservices Architecture](https://martinfowler.com/articles/microservices.html) using [Event Sourcing Pattern](https://www.martinfowler.com/eaaDev/EventSourcing.html).

Here is how it looks in a high level.

<img src="https://github.com/sbamihan/build/blob/master/eBill/diagrams/architecture.png" width="1020"></a>
<br/>

## Table of Contents

-   [Flow](#flow-of-interactions-between-components)
-   [eBill API](#ebill-api)
-   [Bill Stager](#bill-stager)
-   [Bill Retriever](#bill-retriever)
-   [Bill Transporter](#bill-transporter)
-   [**Subscription Service**](#subscription-service)

## Flow of Interactions between Components

1. CC&B posts data to [**eBill API**](https://github.com/sbamihan/build/tree/master/eBill/ebill-api) after BPX
2. **eBill API** publishes Bill Extracted event to Kafka
3. [Bill Stager](https://github.com/sbamihan/build/tree/master/eBill/bill-stager) reacts to Bill Extracted event
4. **Bill Stager** gets subscribed customers' contact info from [**Subscription Service**]https://github.com/sbamihan/build/tree/master/eBill/subscription-service
5. **Bill Stager** calls Bill Retriever to retrieve Bill Info from BPX (expects result coming from step 6)
6. [**Bill Retriever**](https://github.com/sbamihan/build/tree/master/eBill/bill-retriever) fetches Bill Info from BPX (result will be sent back to Bill Stager for finishing touches)
7. **Bill Stager** combines Bill Info (result from step 6) and Account's Contact Info (result from step 4) then saves it to MongoDB
8. After saving to MongoDB, **Bill Stager** publishes Bill Staged event to Kafka
9. [**Bill Transporter**](https://github.com/sbamihan/build/tree/master/eBill/bill-transporter) reacts to Bill Staged event
10. **Bill Transporter** gets staged bills from MongoDb
11. **Bill Transporter** sends staged bills to 3rd party client
12. **eBill API** receives delivery status of sent bills from 3rd party client
13. **eBill API** publishes Bill Delivery Status event to Kafka
14. **Bill Transporter** reacts to Bill Delivery Status event store

    ..14. Another application could also probably react to Bill Delivery Status event to update CC&B
    
15. **Bill Transporter** updates delivery status of the bill on its own data store
	
## eBill API

This is the main interface which serves as the entry point for all interactions from the client. External clients (like **Yondu**) and internal clients like (**CC&B**) can interact with this API thru **REST**.

Procedures:
1.	Receives data from CC&B through eBill API.

    CC&B should be able to POST data through eBill API at `/events` endpoint containing a payload like this. 

    ```json
    {
        "duCode": "dlpc",
        "batchNo": 3107
    }
    ```

    [Reference](https://stackoverflow.com/questions/49769273/call-restful-api-through-oracle-query) for calling restful API through oracle query.

2.	Publishes `BILL-EXTRACTED` event to the event store based on data received from CC&B.

    Event data published in the event store should contain the original data posted from CC&B plus **UUID** and datetime it was created. The data should be similar to this.

    ```json
    {
        "uuid": "65a2b726-1441-498e-84ca-58e0e1c80631",
        "duCode": "dlpc",
        "batchNo": 3107,
        "creDttm": "2020-10-05T05:46:14.144+00:00"
    }
    ```

## Bill Stager

This is the one responsible for preparing and finalizing the bill information of customers who signed up for eBill Service that are included in the extracted bills.

Procedures:
1.  Bill Stager reacts to `BILL-EXTRACTED` event.
2.	Gets customer information (Account ID, email) from **Subscription Service**.

    Should call **Subscription Service** at GET `/{{duCode}}/accounts/search/findByTypeCode?typeCode={{typeCode}}` endpoint, where **duCode** is a value taken from the `BILL-EXTRACTED` event data and **typeCode** is the code for the type of subscription such as **EBIL** (for eBill), **OUTN** (for Outage Notification), or **NEWS** (for News). A sample data structure that this service provides can be found at [Subscription Service](https://github.com/sbamihan/build/tree/master/eBill/subscription-service).

3.	Retrieves bill information of subscribed customers from CC&B through **Bill Retriever**.
    
    Should call **Bill Retriever** at GET `/{{duCode}}/bills/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint, where **duCode** and **batchNo** are values from `BILL-EXTRACTED` event data and **acctNo** is a value from **Subscription Service**.

4.	Projects the bill information then saves to persistence store.

    The projected data should contain the **UUID** from `BILL-EXTRACTED` event data, datetime it was created, and bill information of all accounts who signed up for eBill service that belong on that batch including its contact information taken from **Subscription Service**. A sample structure for the projected data can be found at [Bill Stager](https://github.com/sbamihan/build/tree/master/eBill/bill-stager).

5.	Publishes `BILL-STAGED` event to the event store.

    `BILL-STAGED` event data should be similar to this.

    ```json
    {
        "uuid": "abc085d9-23ca-4183-8d08-86103fcd7532",
        "batchNo": 3107,
        "duCode": "dlpc",
        "creDttm": "2020-10-05T10:43:42.306+00:00",
        "count": 15
    }
    ```

## Bill Retriever

This is the one responsible for fetching the extracted bills from CC&B for the consumption of **Bill Stager**.

Procedures:
1.	Fetches extracted bills from CC&B based on parameters provided by **Bill Stager**.

    Should accept REST call at GET `/bills/search/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint and returns bill information similar to the data found at [Bill Retriever](https://github.com/sbamihan/build/tree/master/eBill/bill-retriever).


## Bill Transporter

This is the one responsible for transporting the staged bills to the 3rd party client over the internet by posting to the client’s API.

Procedures:
1.	Reacts to `BILL-STAGED` event published in the event store.
2.	Gets bill information from persistence store based on data published on `BILL-STAGED` topic from the event store.
3.	Sends fetched bill information to 3rd party client.

    The FINAL data to be transported to 3rd party client should contain the **UUID** from `BILL-STAGED` event data, datetime it was created, and bill information of all accounts who signed up for eBill service that belong on the specified batch including its contact information taken from **Subscription Service**. The data should be similar to the one found at [Bill Transporter](https://github.com/sbamihan/build/tree/master/eBill/bill-transporter).


## Subscription Service

This could be under the **Customer Service** domain. This is the one that manages the customer subscriptions. This is also where **Bill Stager** gets list of customers who signed up for eBill Service.