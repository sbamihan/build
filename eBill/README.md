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

    Should accept REST call at POST `/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint and return bill information similar to this.

    <details>
    <summary>Click to expand!</summary>
    
    ```json
    [
        {
            "tranNo": 43771140,
            "batchCd": "CMBPEXTG",
            "batchNo": 3138,
            "duSetId": 18889,
            "billingBatchNo": "BC22",
            "billColor": "GREEN",
            "courierCode": "33",
            "billType": "R",
            "billMonth": "2020-09-01 00:00:00",
            "billDate": "2020-10-01",
            "dueDate": "2020-10-13 00:00:00",
            "billNo": "096464289996",
            "areaCode": "10",
            "readingBatchNo": null,
            "bookNo": 1323,
            "oldSeqNo": 0,
            "newSeqNo": 62801,
            "crc": "0943717255",
            "acctNo": "0969431381",
            "rateSchedule": "02-R-21",
            "rateScheduleDesc": "Residential 1.1: Rate Schedule Code 21",
            "customerName": "EMPIT,JORITO JR. GUILON",
            "premiseAdd1": "PRK 2 SITIO SAN MIGUEL",
            "premiseAdd2": " ",
            "premiseAdd3": "CAWAYAN, CALINAN",
            "billingAdd1": "PRK 2 SITIO SAN MIGUEL",
            "billingAdd2": " ",
            "billingAdd3": "CAWAYAN, CALINAN NR BRGY. HALL",
            "messageCode": "2047",
            "powerFactorValue": null,
            "billedKwhrCons": 22,
            "billedDemandCons": null,
            "billedKvarCons": null,
            "overdueAmt": -895.75,
            "overdueBillCount": null,
            "billAmt": 107.94,
            "totalAmtDue": -787.81,
            "lastPaymentDate": "2020-09-24 00:00:00",
            "lastPaymentAmount": 1000,
            "mainSaId": "0965370468",
            "messengerCode": "0002",
            "altBillId": "1020530390",
            "locationCode": "176-Y30",
            "lastBillFlg": "N",
            "parMonth": "2020-05-01 00:00:00",
            "parKwhr": 22,
            "completeDate": "2020-10-01 18:45:55",
            "fltConnection": null,
            "fltWattage": null,
            "noBatchPrtSw": "N",
            "ebillOnlySw": "N",
            "extractedOn": "2020-10-01 18:47:23",
            "tin": "711-884-236-000",
            "busActivity": " ",
            "busAdd1": " ",
            "busAdd2": " ",
            "busAdd3": " ",
            "busAdd4": " ",
            "busAdd5": " ",
            "meterDetails": [
                {
                    "meterDetailPK": {
                        "tranNo": 43771140,
                        "badgeNo": "783057"
                    },
                    "serialNo": "85145094",
                    "poleNo": "0345255",
                    "multiplier": 1,
                    "prevReadingDate": "2020-08-25 08:58:00",
                    "currReadingDate": "2020-09-24 09:04:00",
                    "connLoad": 18,
                    "prevKwhrRdg": 1128,
                    "currKwhrRdg": 1150,
                    "regKwhrCons": 22,
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
                    "kvarConsumSubFlg": null
                }
            ],
            "consumptionHistory": [
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-02-25 00:00:00"
                    },
                    "consumption": 41
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-03-25 00:00:00"
                    },
                    "consumption": 33
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-01-24 00:00:00"
                    },
                    "consumption": 69
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2019-10-25 00:00:00"
                    },
                    "consumption": 70
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-09-24 00:00:00"
                    },
                    "consumption": 22
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-08-25 00:00:00"
                    },
                    "consumption": 37
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-06-25 00:00:00"
                    },
                    "consumption": 22
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-05-27 00:00:00"
                    },
                    "consumption": 30
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2020-04-27 00:00:00"
                    },
                    "consumption": 38
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43771140,
                        "rdgDate": "2019-11-25 00:00:00"
                    },
                    "consumption": 40
                }
            ],
            "lineDetails": [
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "FITA-KWH2"
                    },
                    "printPriority": 1127,
                    "description": "     Feed In Tariff Allowance - FIT-All",
                    "rate": "0.0495/kWh",
                    "amount": 1.09
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "LFL-D-F"
                    },
                    "printPriority": 476,
                    "description": "     Lifeline Discount",
                    "rate": "50% x 5.00/month",
                    "amount": -2.5
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "UEC"
                    },
                    "printPriority": 1110,
                    "description": "          Environmental Charge",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vGOVTOT"
                    },
                    "printPriority": 1130,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 15.2
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "NETSPACER"
                    },
                    "printPriority": 1430,
                    "description": null,
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "OVERDUE"
                    },
                    "printPriority": 5,
                    "description": "PREVIOUS BALANCE",
                    "rate": null,
                    "amount": -895.75
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "SVR"
                    },
                    "printPriority": 275,
                    "description": "     Supply Charge",
                    "rate": "0.2367/kWh",
                    "amount": 5.21
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "PREVAMTSPACER"
                    },
                    "printPriority": 6,
                    "description": null,
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "CURCHARGES"
                    },
                    "printPriority": 105,
                    "description": "CURRENT CHARGES",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "ADJLPC"
                    },
                    "printPriority": 540,
                    "description": "     Surcharge",
                    "rate": "2% of 104.25",
                    "amount": 2.09
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "CCBNOTICE"
                    },
                    "printPriority": 1460,
                    "description": "Please pay by due date - 10/13/2020",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "CCBNOTICE1"
                    },
                    "printPriority": 1461,
                    "description": "LAST PAYMENT  -  SEPTEMBER 24, 2020  -  1,000.00",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vGENCHGHDR"
                    },
                    "printPriority": 110,
                    "description": "Generation & Transmission",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "GEN"
                    },
                    "printPriority": 120,
                    "description": "     Generation Charge",
                    "rate": "4.9223/kWh",
                    "amount": 108.29
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "PAR"
                    },
                    "printPriority": 130,
                    "description": "     Power Act Reduction*",
                    "rate": "24.72% x 0.30/kWh",
                    "amount": -1.63
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "TRX-KWH"
                    },
                    "printPriority": 140,
                    "description": "     Transmission Charge",
                    "rate": "0.4454/kWh",
                    "amount": 9.8
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "SYS"
                    },
                    "printPriority": 150,
                    "description": "     System Loss Charge",
                    "rate": "0.4392/kWh",
                    "amount": 9.66
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vGENTRANSTOT"
                    },
                    "printPriority": 190,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 126.12
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vDISTREVHDR"
                    },
                    "printPriority": 200,
                    "description": "Distribution Charges",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "DIST"
                    },
                    "printPriority": 210,
                    "description": "     Distribution Charge",
                    "rate": "1.9371/kWh",
                    "amount": 42.62
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vUNIVCHGHDR"
                    },
                    "printPriority": 1060,
                    "description": "     Universal Charge",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vVATHDR"
                    },
                    "printPriority": 990,
                    "description": "     Value Added Tax",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "MVR"
                    },
                    "printPriority": 280,
                    "description": "     Metering Charge",
                    "rate": "0.1814/kWh",
                    "amount": 3.99
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "MFX"
                    },
                    "printPriority": 290,
                    "description": "     Metering Charge",
                    "rate": "5.00/month",
                    "amount": 5
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vDISTREVTOT"
                    },
                    "printPriority": 310,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 56.82
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vOTHHDR"
                    },
                    "printPriority": 460,
                    "description": "Others",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vOTHTOT"
                    },
                    "printPriority": 590,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": -90.2
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vGOVREVHDR"
                    },
                    "printPriority": 840,
                    "description": "Government Charges",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "FCT"
                    },
                    "printPriority": 920,
                    "description": "     Franchise Tax - Local",
                    "rate": null,
                    "amount": 0.76
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "VAT-GEN"
                    },
                    "printPriority": 1000,
                    "description": "          Generation",
                    "rate": null,
                    "amount": 4.34
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "VAT-TRX"
                    },
                    "printPriority": 1010,
                    "description": "          Transmission",
                    "rate": null,
                    "amount": 0.47
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "VAT-SYS"
                    },
                    "printPriority": 1020,
                    "description": "          System Loss",
                    "rate": null,
                    "amount": 0.41
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "VAT-DIS"
                    },
                    "printPriority": 1030,
                    "description": "          Distribution",
                    "rate": null,
                    "amount": 3.41
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "VAT-OTH"
                    },
                    "printPriority": 1040,
                    "description": "          Others",
                    "rate": null,
                    "amount": 0.34
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "vVATTOT"
                    },
                    "printPriority": 1050,
                    "description": "     Total VAT",
                    "rate": null,
                    "amount": 8.97
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "RESETCOST-KWH"
                    },
                    "printPriority": 307,
                    "description": "     Reset Cost Adjustment",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "UC-ME-SPUG"
                    },
                    "printPriority": 1105,
                    "description": "          Missionary Electrification NPC-SPUG",
                    "rate": "0.1544/kWh",
                    "amount": 3.4
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "UC-ME-RED"
                    },
                    "printPriority": 1106,
                    "description": "          Missionary Electrification RE Developer",
                    "rate": "0.0017/kWh",
                    "amount": 0.04
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "LFTRKWH"
                    },
                    "printPriority": 921,
                    "description": "     Local Franchise Tax Recovery",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "LFL-D"
                    },
                    "printPriority": 475,
                    "description": "     Lifeline Discount",
                    "rate": "50% x 8.1621/kWh",
                    "amount": -89.79
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "USD"
                    },
                    "printPriority": 1120,
                    "description": "          NPC Stranded Debts",
                    "rate": "0.0428/kWh",
                    "amount": 0.94
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "USC"
                    },
                    "printPriority": 1122,
                    "description": "          NPC Stranded Contract Costs",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "UDSC"
                    },
                    "printPriority": 1124,
                    "description": "          DUs Stranded Contract Costs",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "UETR"
                    },
                    "printPriority": 1126,
                    "description": "          Equalization of Taxes & Royalties",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "GREEN_OUTAMT"
                    },
                    "printPriority": 1459,
                    "description": "TOTAL BILL",
                    "rate": null,
                    "amount": -787.81
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43771140,
                        "lineCode": "CURBIL"
                    },
                    "printPriority": 1250,
                    "description": "CURRENT BILL - SEPTEMBER 2020",
                    "rate": null,
                    "amount": 107.94
                }
            ]
        }
    ]
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