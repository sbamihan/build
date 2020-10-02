# eBill 2.0

This `eBill 2.0` is **Aboitiz Power**'s pilot project for implementing [Microservices Architecture](https://martinfowler.com/articles/microservices.html) using [Event Sourcing Pattern](https://www.martinfowler.com/eaaDev/EventSourcing.html).

Here is how looks in a high level.

<img src="https://github.com/sbamihan/build/blob/master/eBill/diagrams/architecture.png" width="1020"></a>
<br/>

## Table of Contents

-   [eBill API](#ebill-api)
-   [Bill Stager](#bill-stager)
-   [Bill Retriever](#bill-retriever)
-   [Bill Transporter](#bill-transporter)
-   [**Subscription Service**](#subscription-service)
	
## eBill API

This is the main interface which serves as the entry point for all interactions from the client. External clients (like **Yondu**) and internal clients like (**CC&B**) can interact with this API thru **REST**.

Procedures:
1.	Receives data from CC&B thru POST `/events` endpoint
2.	Publishes `BILL-EXTRACTED` event to the event store based on data received from CC&B

## Bill Stager

This is the one responsible for preparing and finalizing the bill information of customers who signed up for eBill Service that are included in the extracted bills.

Procedures:
1.	Gets customer information (Account ID, email) from **Subscription Service**
2.	Retrieves bill information of subscribed customers from CC&B through **Bill Retriever**
3.	Projects the bill information then saves to persistence store
4.	Publishes `BILL-STAGED` event to the event store


## Bill Retriever

This is the one responsible for fetching the extracted bills from CC&B for the consumption of **Bill Stager**.

Procedures:
1.	Fetches extracted bills from CC&B based on parameters provided by **Bill Stager**


## Bill Transporter

This is the one responsible for transporting the staged bills to the 3rd party client over the internet by posting to the client’s API.

Procedures:
1.	Reacts to `BILL-STAGED` event published in the event store
2.	Gets bill information from persistence store based on data published on `BILL-STAGED` topic from the event store
3.	Sends fetched bill information to 3rd party client


## Subscription Service

This could be under the **Customer Service** domain. This is the one that manages the customer subscriptions. This is also where **Bill Stager** gets list of customers who signed up for eBill Service.