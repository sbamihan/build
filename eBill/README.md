# eBill 2.0

This `eBill 2.0` is **Aboitiz Power**'s pilot project for implementing [Microservices Architecture](https://martinfowler.com/articles/microservices.html) using [Event Sourcing Pattern](https://www.martinfowler.com/eaaDev/EventSourcing.html).

Here is how it looks in a high level.

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

    Event data published in the event store should contain the original data posted from CC&B plus UUID and datetime it was created. The data should be similar to this.

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
3.	Retrieves bill information of subscribed customers from CC&B through **Bill Retriever**.
    
    Should call **Bill Retriever** at endpoint GET `/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}`, where **batchNo** is a value from `BILL-EXTRACTED` event data and **acctNo** is a value from **Subscription Service**.

4.	Projects the bill information then saves to persistence store.
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

    Should accept REST call at POST `/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` and return bill information similar to this.

    <details>
    <summary>Click to expand!</summary>
    
    ```json
    {
        "uuid": "abc085d9-23ca-4183-8d08-86103fcd7532",
        "creDttm": "2020-10-05T10:43:54.994+00:00",
        "billCollection": [
            {
                "tranNo": 43772494,
                "batchCd": "CMBPEXTR",
                "batchNo": 3107,
                "duSetId": 18892,
                "billingBatchNo": "BC01",
                "billColor": "RED",
                "courierCode": "34",
                "billType": "R",
                "billMonth": "2020-10-01 00:00:00",
                "billDate": "2020-10-02",
                "dueDate": "2020-10-14 00:00:00",
                "billNo": "492670196895",
                "areaCode": "10",
                "readingBatchNo": null,
                "bookNo": 694,
                "oldSeqNo": 0,
                "newSeqNo": 5,
                "crc": "0941276089",
                "acctNo": "4925321111",
                "rateSchedule": "03-C-43",
                "rateScheduleDesc": "Secondary Retail 2.1: Rate Schedule Code 43",
                "customerName": "DIGITEL MOBILE PHILS. INC.,",
                "premiseAdd1": "KM. 22 BUDBUD,BUNAWAN",
                "premiseAdd2": " ",
                "premiseAdd3": " ",
                "billingAdd1": "PILIPINO TELEPHONE CORP.",
                "billingAdd2": "Matina Shrine Hills /Pole#0164992",
                "billingAdd3": "  Meter#451922",
                "messageCode": "2047",
                "powerFactorValue": null,
                "billedKwhrCons": 3299,
                "billedDemandCons": null,
                "billedKvarCons": null,
                "overdueAmt": 1546.9,
                "overdueBillCount": 1,
                "billAmt": 30992.98,
                "totalAmtDue": 32539.88,
                "lastPaymentDate": "2020-09-11 00:00:00",
                "lastPaymentAmount": 35253.47,
                "mainSaId": "4925321817",
                "messengerCode": "0006",
                "altBillId": "1020544567",
                "locationCode": "111-R18",
                "lastBillFlg": "N",
                "parMonth": "2020-08-01 00:00:00",
                "parKwhr": 0,
                "completeDate": "2020-10-03 00:40:36",
                "fltConnection": null,
                "fltWattage": null,
                "noBatchPrtSw": "N",
                "ebillOnlySw": "N",
                "extractedOn": "2020-10-03 02:03:04",
                "tin": "215-398-626",
                "busActivity": " ",
                "busAdd1": " ",
                "busAdd2": " ",
                "busAdd3": " ",
                "busAdd4": " ",
                "busAdd5": " ",
                "meterDetailsCollection": [
                    {
                        "meterDetailsPK": {
                            "tranNo": 43772494,
                            "badgeNo": "823572"
                        },
                        "serialNo": "41055449",
                        "poleNo": "0616912",
                        "multiplier": 1,
                        "prevReadingDate": "2020-09-02 13:09:00",
                        "currReadingDate": "2020-10-02 12:05:00",
                        "connLoad": 20000,
                        "prevKwhrRdg": 69308,
                        "currKwhrRdg": 72607,
                        "regKwhrCons": 3299,
                        "prevDemandRdg": null,
                        "currDemandRdg": null,
                        "regDemandCons": null,
                        "prevKvarRdg": null,
                        "currKvarRdg": null,
                        "regKvarCons": null,
                        "meterType": null,
                        "consumSubFlg": null,
                        "kwhrConsumSubFlg": null,
                        "demandConsumSubFlg": null,
                        "kvarConsumSubFlg": null,
                        "bill": null
                    }
                ],
                "consumptionHistoryCollection": [
                    {
                        "consumptionHistoryPK": {
                            "tranNo": 43772494,
                            "rdgDate": "2019-11-02 00:00:00"
                        },
                        "consumption": 4056,
                        "bill": null
                    },
                    {
                        "consumptionHistoryPK": {
                            "tranNo": 43772494,
                            "rdgDate": "2020-08-02 00:00:00"
                        },
                        "consumption": 3792,
                        "bill": null
                    },
                    {
                        "consumptionHistoryPK": {
                            "tranNo": 43772494,
                            "rdgDate": "2020-06-02 00:00:00"
                        },
                        "consumption": 3910,
                        "bill": null
                    }
                ],
                "lineDetailsCollection": [
                    {
                        "lineDetailsPK": {
                            "tranNo": 43772494,
                            "lineCode": "FITA-KWH2"
                        },
                        "printPriority": 1127,
                        "description": "     Feed In Tariff Allowance - FIT-All",
                        "rate": "0.0495/kWh",
                        "amount": 163.3,
                        "bill": null
                    },
                    {
                        "lineDetailsPK": {
                            "tranNo": 43772494,
                            "lineCode": "R-SLF"
                        },
                        "printPriority": 485,
                        "description": "     Senior Citizen Subsidy",
                        "rate": "0.00003/kWh",
                        "amount": 0.1,
                        "bill": null
                    },
                    {
                        "lineDetailsPK": {
                            "tranNo": 43772494,
                            "lineCode": "UEC"
                        },
                        "printPriority": 1110,
                        "description": "          Environmental Charge",
                        "rate": "0.00/kWh",
                        "amount": 0,
                        "bill": null
                    }
                ],
                "contactCollection": [
                    {
                        "acctNo": "4925321111",
                        "contactType": "email",
                        "value": "willy.Olanio@digitel.ph"
                    }
                ],
                "uuid": "abc085d9-23ca-4183-8d08-86103fcd7532"
            }
        ]
    }
    ```
    </details>

## Bill Transporter

This is the one responsible for transporting the staged bills to the 3rd party client over the internet by posting to the client’s API.

Procedures:
1.	Reacts to `BILL-STAGED` event published in the event store.
2.	Gets bill information from persistence store based on data published on `BILL-STAGED` topic from the event store.
3.	Sends fetched bill information to 3rd party client.


## Subscription Service

This could be under the **Customer Service** domain. This is the one that manages the customer subscriptions. This is also where **Bill Stager** gets list of customers who signed up for eBill Service.