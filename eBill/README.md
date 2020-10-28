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

    Should call **Subscription Service** at GET `/{{duCode}}/accounts/search/findByTypeCode?typeCode={{typeCode}}` endpoint, where **duCode** is a value taken from the `BILL-EXTRACTED` event data and **typeCode** is the code for the type of subscription such as **EBIL** (for eBill), **OUTN** (for Outage Notification), or **NEWS** (for News). The data should be similar to this.

    <details>
    <summary>Click to expand!</summary>
    
    ```json
    [
        {
            "accountId": "0002709352",
            "accountName": "MARYANNSACAY2014",
            "accountSubscriptions": [
                {
                    "subscribe": "Y",
                    "subscriptionType": {
                        "typeCode": "EBIL",
                        "description": "Electronic sending of bill"
                    }
                }
            ],
            "accountContacts": [
                {
                    "value": "maryannsacay2014@gmail.com",
                    "contactType": {
                        "typeCode": "EADD",
                        "description": "Email Address"
                    }
                }
            ]
        },
        {
            "accountId": "0599421111",
            "accountName": "Test Account",
            "accountSubscriptions": [
                {
                    "subscribe": "Y",
                    "subscriptionType": {
                        "typeCode": "EBIL",
                        "description": "Electronic sending of bill"
                    }
                },
                {
                    "subscribe": "Y",
                    "subscriptionType": {
                        "typeCode": "OUTN",
                        "description": "Outage notification"
                    }
                }
            ],
            "accountContacts": [
                {
                    "value": "sherwinamihan@gmail.com",
                    "contactType": {
                        "typeCode": "EADD",
                        "description": "Email Address"
                    }
                },
                {
                    "value": "sherwin.amihan@aboitiz.com",
                    "contactType": {
                        "typeCode": "EADD",
                        "description": "Email Address"
                    }
                }
            ]
        }
    ]
    ```
    </details>

3.	Retrieves bill information of subscribed customers from CC&B through **Bill Retriever**.
    
    Should call **Bill Retriever** at GET `/{{duCode}}/bills/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint, where **duCode** and **batchNo** are values from `BILL-EXTRACTED` event data and **acctNo** is a value from **Subscription Service**.

4.	Projects the bill information then saves to persistence store.

    The projected data should contain the **UUID** from `BILL-EXTRACTED` event data, datetime it was created, and bill information of all accounts who signed up for eBill service that belong on that batch including its contact information taken from **Subscription Service**. The data should be similar to this.

    <details>
    <summary>Click to expand!</summary>
    
    ```json
    {
        "tranNo": 43772494,
        "batchCd": "CMBPEXTR",
        "batchNo": 3107,
        "duSetId": 18892,
        "billingBatchNo": "BC01",
        "billColor": "RED",
        "courierCode": "34",
        "billType": "R",
        "billMonth": "2020-10-0100:00:00",
        "billDate": "2020-10-02",
        "dueDate": "2020-10-1400:00:00",
        "billNo": "492670196895",
        "areaCode": "10",
        "readingBatchNo": null,
        "bookNo": 694,
        "oldSeqNo": 0,
        "newSeqNo": 5,
        "crc": "0941276089",
        "acctNo": "4925321111",
        "rateSchedule": "03-C-43",
        "rateScheduleDesc": "SecondaryRetail2.1:RateScheduleCode43",
        "customerName": "DIGITELMOBILEPHILS.INC.,",
        "premiseAdd1": "KM.22BUDBUD,BUNAWAN",
        "premiseAdd2": "",
        "premiseAdd3": "",
        "billingAdd1": "PILIPINOTELEPHONECORP.",
        "billingAdd2": "MatinaShrineHills/Pole#0164992",
        "billingAdd3": "Meter#451922",
        "messageCode": "2047",
        "powerFactorValue": null,
        "billedKwhrCons": 3299,
        "billedDemandCons": null,
        "billedKvarCons": null,
        "overdueAmt": 1546.9,
        "overdueBillCount": 1,
        "billAmt": 30992.98,
        "totalAmtDue": 32539.88,
        "lastPaymentDate": "2020-09-1100:00:00",
        "lastPaymentAmount": 35253.47,
        "mainSaId": "4925321817",
        "messengerCode": "0006",
        "altBillId": "1020544567",
        "locationCode": "111-R18",
        "lastBillFlg": "N",
        "parMonth": "2020-08-0100:00:00",
        "parKwhr": 0,
        "completeDate": "2020-10-0300:40:36",
        "fltConnection": null,
        "fltWattage": null,
        "noBatchPrtSw": "N",
        "ebillOnlySw": "N",
        "extractedOn": "2020-10-0302:03:04",
        "tin": "215-398-626",
        "busActivity": "",
        "busAdd1": "",
        "busAdd2": "",
        "busAdd3": "",
        "busAdd4": "",
        "busAdd5": "",
        "meterDetails": [
            {
                "meterDetailPK": {
                    "tranNo": 43772494,
                    "badgeNo": "823572"
                },
                "serialNo": "41055449",
                "poleNo": "0616912",
                "multiplier": 1,
                "prevReadingDate": "2020-09-0213:09:00",
                "currReadingDate": "2020-10-0212:05:00",
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
                "kvarConsumSubFlg": null
            }
        ],
        "consumptionHistory": null,
        "lineDetails": [
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "FITA-KWH2"
                },
                "printPriority": 1127,
                "description": "FeedInTariffAllowance-FIT-All",
                "rate": "0.0495/kWh",
                "amount": 163.3
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "R-SLF"
                },
                "printPriority": 485,
                "description": "SeniorCitizenSubsidy",
                "rate": "0.00003/kWh",
                "amount": 0.1
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "UEC"
                },
                "printPriority": 1110,
                "description": "EnvironmentalCharge",
                "rate": "0.00/kWh",
                "amount": 0
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vGOVTOT"
                },
                "printPriority": 1130,
                "description": "Sub-Total",
                "rate": null,
                "amount": 3673.42
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "NETSPACER"
                },
                "printPriority": 1430,
                "description": null,
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "OVERDUE"
                },
                "printPriority": 5,
                "description": "PREVIOUSBALANCE",
                "rate": null,
                "amount": 1546.9
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "SVR"
                },
                "printPriority": 275,
                "description": "SupplyCharge",
                "rate": "0.2367/kWh",
                "amount": 780.87
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "PREVAMTSPACER"
                },
                "printPriority": 6,
                "description": null,
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "CURCHARGES"
                },
                "printPriority": 105,
                "description": "CURRENTCHARGES",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "ADJLPC"
                },
                "printPriority": 540,
                "description": "Surcharge",
                "rate": "2%of1,405.53",
                "amount": 28.11
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "CCBREDNOTICE"
                },
                "printPriority": 1460,
                "description": "DISCONNECTION/DUEDATE:48hoursfromreceipthereof",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "CCBNOTICE1"
                },
                "printPriority": 1461,
                "description": "LASTPAYMENT-SEPTEMBER11,2020-35,253.47",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vGENCHGHDR"
                },
                "printPriority": 110,
                "description": "Generation&Transmission",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "GEN"
                },
                "printPriority": 120,
                "description": "GenerationCharge",
                "rate": "4.9223/kWh",
                "amount": 16238.67
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "TRX-KWH"
                },
                "printPriority": 140,
                "description": "TransmissionCharge",
                "rate": "0.6503/kWh",
                "amount": 2145.34
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "SYS"
                },
                "printPriority": 150,
                "description": "SystemLossCharge",
                "rate": "0.456/kWh",
                "amount": 1504.34
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vGENTRANSTOT"
                },
                "printPriority": 190,
                "description": "Sub-Total",
                "rate": null,
                "amount": 19888.35
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vDISTREVHDR"
                },
                "printPriority": 200,
                "description": "DistributionCharges",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "DIST"
                },
                "printPriority": 210,
                "description": "DistributionCharge",
                "rate": "1.9371/kWh",
                "amount": 6390.49
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vUNIVCHGHDR"
                },
                "printPriority": 1060,
                "description": "UniversalCharge",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vVATHDR"
                },
                "printPriority": 990,
                "description": "ValueAddedTax",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "MVR"
                },
                "printPriority": 280,
                "description": "MeteringCharge",
                "rate": "0.1814/kWh",
                "amount": 598.44
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "MFX"
                },
                "printPriority": 290,
                "description": "MeteringCharge",
                "rate": "5.00/month",
                "amount": 5
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vDISTREVTOT"
                },
                "printPriority": 310,
                "description": "Sub-Total",
                "rate": null,
                "amount": 7774.8
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vOTHHDR"
                },
                "printPriority": 460,
                "description": "Others",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "SLF-C"
                },
                "printPriority": 470,
                "description": "LifelineRateSubsidy",
                "rate": "0.0617/kWh",
                "amount": 203.55
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vOTHTOT"
                },
                "printPriority": 590,
                "description": "Sub-Total",
                "rate": null,
                "amount": -343.59
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vGOVREVHDR"
                },
                "printPriority": 840,
                "description": "GovernmentCharges",
                "rate": null,
                "amount": null
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "FCT"
                },
                "printPriority": 920,
                "description": "FranchiseTax-Local",
                "rate": null,
                "amount": 224.02
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "VAT-GEN"
                },
                "printPriority": 1000,
                "description": "Generation",
                "rate": null,
                "amount": 1302.13
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "VAT-TRX"
                },
                "printPriority": 1010,
                "description": "Transmission",
                "rate": null,
                "amount": 211.47
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "VAT-SYS"
                },
                "printPriority": 1020,
                "description": "SystemLoss",
                "rate": null,
                "amount": 128.65
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "VAT-DIS"
                },
                "printPriority": 1030,
                "description": "Distribution",
                "rate": null,
                "amount": 932.98
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "VAT-OTH"
                },
                "printPriority": 1040,
                "description": "Others",
                "rate": null,
                "amount": 54.69
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "vVATTOT"
                },
                "printPriority": 1050,
                "description": "TotalVAT",
                "rate": null,
                "amount": 2629.92
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "NPC_ADJKWH"
                },
                "printPriority": 488,
                "description": "NPC/PSALMAdjustment",
                "rate": "-0.1744/kWh",
                "amount": -575.35
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "RESETCOST-KWH"
                },
                "printPriority": 307,
                "description": "ResetCostAdjustment",
                "rate": "0.00/kWh",
                "amount": 0
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "UC-ME-SPUG"
                },
                "printPriority": 1105,
                "description": "MissionaryElectrificationNPC-SPUG",
                "rate": "0.1544/kWh",
                "amount": 509.37
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "UC-ME-RED"
                },
                "printPriority": 1106,
                "description": "MissionaryElectrificationREDeveloper",
                "rate": "0.0017/kWh",
                "amount": 5.61
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "LFTRKWH"
                },
                "printPriority": 921,
                "description": "LocalFranchiseTaxRecovery",
                "rate": "0.00/kWh",
                "amount": 0
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "USD"
                },
                "printPriority": 1120,
                "description": "NPCStrandedDebts",
                "rate": "0.0428/kWh",
                "amount": 141.2
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "USC"
                },
                "printPriority": 1122,
                "description": "NPCStrandedContractCosts",
                "rate": "0.00/kWh",
                "amount": 0
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "UDSC"
                },
                "printPriority": 1124,
                "description": "DUsStrandedContractCosts",
                "rate": "0.00/kWh",
                "amount": 0
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "UETR"
                },
                "printPriority": 1126,
                "description": "EqualizationofTaxes&Royalties",
                "rate": "0.00/kWh",
                "amount": 0
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "RED_OUTAMT"
                },
                "printPriority": 1459,
                "description": "PLEASEPAYYOURTOTALBILL",
                "rate": null,
                "amount": 32539.88
            },
            {
                "lineDetailPK": {
                    "tranNo": 43772494,
                    "lineCode": "CURBIL"
                },
                "printPriority": 1250,
                "description": "CURRENTBILL-OCTOBER2020",
                "rate": null,
                "amount": 30992.98
            }
        ],
        "contacts": [
            {
                "acctNo": "4925321111",
                "contactType": "email",
                "value": "willy.Olanio@digitel.ph"
            }
        ],
        "uuid": "8a70ce7c-7bf3-44ef-b198-b4376d88d768"
    }
    ```
    </details>

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

    Should accept REST call at POST `/bills/search/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint and return bill information similar to this.

    <details>
    <summary>Click to expand!</summary>
    
    ```json
        [
        {
            "tranNo": 43829514,
            "batchCd": "CMBPEXTG",
            "batchNo": 3142,
            "duSetId": 18897,
            "billingBatchNo": "BC03",
            "billColor": "GREEN",
            "courierCode": "33",
            "billType": "R",
            "billMonth": "2020-10-01 00:00:00",
            "billDate": "2020-10-05",
            "dueDate": "2020-10-17 00:00:00",
            "billNo": "038234325419",
            "areaCode": "10",
            "readingBatchNo": null,
            "bookNo": 524,
            "oldSeqNo": 0,
            "newSeqNo": 114802,
            "crc": "0943148315",
            "acctNo": "0381503733",
            "rateSchedule": "02-R-21",
            "rateScheduleDesc": "Residential 1.1: Rate Schedule Code 21",
            "customerName": "SASIN,ANTHONY BARLIS",
            "premiseAdd1": "B6 L31",
            "premiseAdd2": "FAIRLANES",
            "premiseAdd3": "ANGLIONGTO",
            "billingAdd1": "B6 L31",
            "billingAdd2": "FAIRLANES",
            "billingAdd3": "ANGLIONGTO  ",
            "powerFactorValue": null,
            "billedKwhrCons": 1393,
            "billedDemandCons": 8.563,
            "billedKvarCons": null,
            "overdueAmt": -0.52,
            "overdueBillCount": null,
            "billAmt": 13281.75,
            "totalAmtDue": 13276.42,
            "lastPaymentDate": "2020-09-19 00:00:00",
            "lastPaymentAmount": 12712.01,
            "mainSaId": "0385664312",
            "altBillId": "1020575318",
            "parMonth": "2020-08-01 00:00:00",
            "parKwhr": 1228,
            "completeDate": "2020-10-05 21:06:01",
            "noBatchPrtSw": "N",
            "ebillOnlySw": "N",
            "extractedOn": "2020-10-06 03:04:29",
            "tin": null,
            "busActivity": " ",
            "busAdd1": " ",
            "busAdd2": " ",
            "busAdd3": " ",
            "busAdd4": " ",
            "busAdd5": " ",
            "billMessage": {
                "messageCode": "2047",
                "description": "ECQ Online Payment",
                "messageText": "Check your monthly electric bill in just a few taps with Davao Light's mobile application, mobileAP.\n\nmobileAP allows you to:\n- check your monthly electricity bill\n- view your payment history\n- see list of payment options\n\nmobileAP is available for download in the App Store and Google Play Store. Just key in mobileAP or Davao Light in the search tab.\n\nDownload mobileAP now!"
            },
            "meterDetails": [
                {
                    "meterDetailPK": {
                        "tranNo": 43829514,
                        "badgeNo": "2SL2019094239"
                    },
                    "serialNo": "154106076",
                    "poleNo": "1435921",
                    "multiplier": 1,
                    "prevReadingDate": "2020-09-17 11:00:00",
                    "currReadingDate": "2020-10-05 12:27:00",
                    "connLoad": 8000,
                    "prevKwhrRdg": 0,
                    "currKwhrRdg": 789,
                    "regKwhrCons": 789,
                    "prevDemandRdg": 0,
                    "currDemandRdg": 8.563,
                    "regDemandCons": 8.563,
                    "prevKvarRdg": null,
                    "currKvarRdg": null,
                    "regKvarCons": null,
                    "meterType": null
                },
                {
                    "meterDetailPK": {
                        "tranNo": 43829514,
                        "badgeNo": "746658"
                    },
                    "serialNo": "85102878",
                    "poleNo": "1435921",
                    "multiplier": 1,
                    "prevReadingDate": "2020-09-04 14:21:00",
                    "currReadingDate": "2020-09-17 10:45:00",
                    "connLoad": 8000,
                    "prevKwhrRdg": 60271,
                    "currKwhrRdg": 60875,
                    "regKwhrCons": 604,
                    "prevDemandRdg": null,
                    "currDemandRdg": null,
                    "regDemandCons": null,
                    "prevKvarRdg": null,
                    "currKvarRdg": null,
                    "regKvarCons": null,
                    "meterType": null
                }
            ],
            "consumptionHistory": [
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-09-04 00:00:00"
                    },
                    "consumption": 1342
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-05-05 00:00:00"
                    },
                    "consumption": 1419
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-02-04 00:00:00"
                    },
                    "consumption": 1203
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2019-10-04 00:00:00"
                    },
                    "consumption": 1498
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-06-04 00:00:00"
                    },
                    "consumption": 1505
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2019-12-04 00:00:00"
                    },
                    "consumption": 1298
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2019-11-04 00:00:00"
                    },
                    "consumption": 1391
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-10-05 00:00:00"
                    },
                    "consumption": 1393
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-01-04 00:00:00"
                    },
                    "consumption": 1222
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-07-04 00:00:00"
                    },
                    "consumption": 1316
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-03-04 00:00:00"
                    },
                    "consumption": 1036
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-04-04 00:00:00"
                    },
                    "consumption": 1280
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 43829514,
                        "rdgDate": "2020-08-04 00:00:00"
                    },
                    "consumption": 1228
                }
            ],
            "lineDetails": [
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "FITA-KWH2"
                    },
                    "printPriority": 1127,
                    "description": "     Feed In Tariff Allowance - FIT-All",
                    "rate": "0.0495/kWh",
                    "amount": 68.95
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "R-SLF"
                    },
                    "printPriority": 485,
                    "description": "     Senior Citizen Subsidy",
                    "rate": "0.00003/kWh",
                    "amount": 0.04
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "UEC"
                    },
                    "printPriority": 1110,
                    "description": "          Environmental Charge",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vGOVTOT"
                    },
                    "printPriority": 1130,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 1657.79
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "NETSPACER"
                    },
                    "printPriority": 1430,
                    "description": null,
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "OVERDUE"
                    },
                    "printPriority": 5,
                    "description": "PREVIOUS BALANCE",
                    "rate": null,
                    "amount": -0.52
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "SVR"
                    },
                    "printPriority": 275,
                    "description": "     Supply Charge",
                    "rate": "0.2367/kWh",
                    "amount": 329.72
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "PREVAMTSPACER"
                    },
                    "printPriority": 6,
                    "description": null,
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "CURCHARGES"
                    },
                    "printPriority": 105,
                    "description": "CURRENT CHARGES",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "ADJLPC"
                    },
                    "printPriority": 540,
                    "description": "     Surcharge",
                    "rate": "2% of 12,711.49",
                    "amount": 254.23
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "CCBNOTICE"
                    },
                    "printPriority": 1460,
                    "description": "Please pay by due date - 10/17/2020",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "CCBNOTICE1"
                    },
                    "printPriority": 1461,
                    "description": "LAST PAYMENT  -  SEPTEMBER 19, 2020  -  12,712.01",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vGENCHGHDR"
                    },
                    "printPriority": 110,
                    "description": "Generation & Transmission",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "GEN"
                    },
                    "printPriority": 120,
                    "description": "     Generation Charge",
                    "rate": "4.9223/kWh",
                    "amount": 6856.76
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "PAR"
                    },
                    "printPriority": 130,
                    "description": "     Power Act Reduction*",
                    "rate": "24.72% x 0.30/kWh",
                    "amount": -91.06
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "TRX-KWH"
                    },
                    "printPriority": 140,
                    "description": "     Transmission Charge",
                    "rate": "0.4454/kWh",
                    "amount": 620.44
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "SYS"
                    },
                    "printPriority": 150,
                    "description": "     System Loss Charge",
                    "rate": "0.4392/kWh",
                    "amount": 611.81
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vGENTRANSTOT"
                    },
                    "printPriority": 190,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 7997.95
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vDISTREVHDR"
                    },
                    "printPriority": 200,
                    "description": "Distribution Charges",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "DIST"
                    },
                    "printPriority": 210,
                    "description": "     Distribution Charge",
                    "rate": "1.9371/kWh",
                    "amount": 2698.38
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vUNIVCHGHDR"
                    },
                    "printPriority": 1060,
                    "description": "     Universal Charge",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vVATHDR"
                    },
                    "printPriority": 990,
                    "description": "     Value Added Tax",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "MVR"
                    },
                    "printPriority": 280,
                    "description": "     Metering Charge",
                    "rate": "0.1814/kWh",
                    "amount": 252.69
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "MFX"
                    },
                    "printPriority": 290,
                    "description": "     Metering Charge",
                    "rate": "5.00/month",
                    "amount": 5
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vDISTREVTOT"
                    },
                    "printPriority": 310,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 3285.79
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vOTHHDR"
                    },
                    "printPriority": 460,
                    "description": "Others",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "SLF-C"
                    },
                    "printPriority": 470,
                    "description": "     Lifeline Rate Subsidy",
                    "rate": "0.0617/kWh",
                    "amount": 85.95
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vOTHTOT"
                    },
                    "printPriority": 590,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 340.22
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vGOVREVHDR"
                    },
                    "printPriority": 840,
                    "description": "Government Charges",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "FCT"
                    },
                    "printPriority": 920,
                    "description": "     Franchise Tax - Local",
                    "rate": null,
                    "amount": 95.32
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "VAT-GEN"
                    },
                    "printPriority": 1000,
                    "description": "          Generation",
                    "rate": null,
                    "amount": 549.81
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "VAT-TRX"
                    },
                    "printPriority": 1010,
                    "description": "          Transmission",
                    "rate": null,
                    "amount": 61.16
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "VAT-SYS"
                    },
                    "printPriority": 1020,
                    "description": "          System Loss",
                    "rate": null,
                    "amount": 54.33
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "VAT-DIS"
                    },
                    "printPriority": 1030,
                    "description": "          Distribution",
                    "rate": null,
                    "amount": 394.29
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "VAT-OTH"
                    },
                    "printPriority": 1040,
                    "description": "          Others",
                    "rate": null,
                    "amount": 52.26
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "vVATTOT"
                    },
                    "printPriority": 1050,
                    "description": "     Total VAT",
                    "rate": null,
                    "amount": 1111.85
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "RESETCOST-KWH"
                    },
                    "printPriority": 307,
                    "description": "     Reset Cost Adjustment",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "NET-E"
                    },
                    "printPriority": 1300,
                    "description": "Credit Adjustment",
                    "rate": null,
                    "amount": -4.81
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "UC-ME-SPUG"
                    },
                    "printPriority": 1105,
                    "description": "          Missionary Electrification NPC-SPUG",
                    "rate": "0.1544/kWh",
                    "amount": 215.08
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "UC-ME-RED"
                    },
                    "printPriority": 1106,
                    "description": "          Missionary Electrification RE Developer",
                    "rate": "0.0017/kWh",
                    "amount": 2.37
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "LFTRKWH"
                    },
                    "printPriority": 921,
                    "description": "     Local Franchise Tax Recovery",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "ENT"
                    },
                    "printPriority": 930,
                    "description": "     Energy Tax",
                    "rate": null,
                    "amount": 104.6
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "USD"
                    },
                    "printPriority": 1120,
                    "description": "          NPC Stranded Debts",
                    "rate": "0.0428/kWh",
                    "amount": 59.62
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "USC"
                    },
                    "printPriority": 1122,
                    "description": "          NPC Stranded Contract Costs",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "UDSC"
                    },
                    "printPriority": 1124,
                    "description": "          DUs Stranded Contract Costs",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "UETR"
                    },
                    "printPriority": 1126,
                    "description": "          Equalization of Taxes & Royalties",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "GREEN_OUTAMT"
                    },
                    "printPriority": 1459,
                    "description": "TOTAL BILL",
                    "rate": null,
                    "amount": 13276.42
                },
                {
                    "lineDetailPK": {
                        "tranNo": 43829514,
                        "lineCode": "CURBIL"
                    },
                    "printPriority": 1250,
                    "description": "CURRENT BILL - OCTOBER 2020",
                    "rate": null,
                    "amount": 13281.75
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

    The FINAL data to be transported to 3rd party client should contain the **UUID** from `BILL-STAGED` event data, datetime it was created, and bill information of all accounts who signed up for eBill service that belong on the specified batch including its contact information taken from **Subscription Service**. The data should be similar to this.

    <details>
    <summary>Click to expand!</summary>
    
    ```json
    {
        "uuid": "8a70ce7c-7bf3-44ef-b198-b4376d88d768",
        "creDttm": "2020-10-06T02:31:04.134+00:00",
        "bills": [
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
                "meterDetails": [
                    {
                        "meterDetailPK": {
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
                        "kvarConsumSubFlg": null
                    }
                ],
                "consumptionHistory": null,
                "lineDetails": [
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "FITA-KWH2"
                        },
                        "printPriority": 1127,
                        "description": "     Feed In Tariff Allowance - FIT-All",
                        "rate": "0.0495/kWh",
                        "amount": 163.3
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "R-SLF"
                        },
                        "printPriority": 485,
                        "description": "     Senior Citizen Subsidy",
                        "rate": "0.00003/kWh",
                        "amount": 0.1
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "UEC"
                        },
                        "printPriority": 1110,
                        "description": "          Environmental Charge",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vGOVTOT"
                        },
                        "printPriority": 1130,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": 3673.42
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "NETSPACER"
                        },
                        "printPriority": 1430,
                        "description": null,
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "OVERDUE"
                        },
                        "printPriority": 5,
                        "description": "PREVIOUS BALANCE",
                        "rate": null,
                        "amount": 1546.9
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "SVR"
                        },
                        "printPriority": 275,
                        "description": "     Supply Charge",
                        "rate": "0.2367/kWh",
                        "amount": 780.87
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "PREVAMTSPACER"
                        },
                        "printPriority": 6,
                        "description": null,
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "CURCHARGES"
                        },
                        "printPriority": 105,
                        "description": "CURRENT CHARGES",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "ADJLPC"
                        },
                        "printPriority": 540,
                        "description": "     Surcharge",
                        "rate": "2% of 1,405.53",
                        "amount": 28.11
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "CCBREDNOTICE"
                        },
                        "printPriority": 1460,
                        "description": "DISCONNECTION/DUE DATE:48 hours from receipt hereof",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "CCBNOTICE1"
                        },
                        "printPriority": 1461,
                        "description": "LAST PAYMENT  -  SEPTEMBER 11, 2020  -  35,253.47",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vGENCHGHDR"
                        },
                        "printPriority": 110,
                        "description": "Generation & Transmission",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "GEN"
                        },
                        "printPriority": 120,
                        "description": "     Generation Charge",
                        "rate": "4.9223/kWh",
                        "amount": 16238.67
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "TRX-KWH"
                        },
                        "printPriority": 140,
                        "description": "     Transmission Charge",
                        "rate": "0.6503/kWh",
                        "amount": 2145.34
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "SYS"
                        },
                        "printPriority": 150,
                        "description": "     System Loss Charge",
                        "rate": "0.456/kWh",
                        "amount": 1504.34
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vGENTRANSTOT"
                        },
                        "printPriority": 190,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": 19888.35
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vDISTREVHDR"
                        },
                        "printPriority": 200,
                        "description": "Distribution Charges",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "DIST"
                        },
                        "printPriority": 210,
                        "description": "     Distribution Charge",
                        "rate": "1.9371/kWh",
                        "amount": 6390.49
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vUNIVCHGHDR"
                        },
                        "printPriority": 1060,
                        "description": "     Universal Charge",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vVATHDR"
                        },
                        "printPriority": 990,
                        "description": "     Value Added Tax",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "MVR"
                        },
                        "printPriority": 280,
                        "description": "     Metering Charge",
                        "rate": "0.1814/kWh",
                        "amount": 598.44
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "MFX"
                        },
                        "printPriority": 290,
                        "description": "     Metering Charge",
                        "rate": "5.00/month",
                        "amount": 5
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vDISTREVTOT"
                        },
                        "printPriority": 310,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": 7774.8
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vOTHHDR"
                        },
                        "printPriority": 460,
                        "description": "Others",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "SLF-C"
                        },
                        "printPriority": 470,
                        "description": "     Lifeline Rate Subsidy",
                        "rate": "0.0617/kWh",
                        "amount": 203.55
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vOTHTOT"
                        },
                        "printPriority": 590,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": -343.59
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vGOVREVHDR"
                        },
                        "printPriority": 840,
                        "description": "Government Charges",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "FCT"
                        },
                        "printPriority": 920,
                        "description": "     Franchise Tax - Local",
                        "rate": null,
                        "amount": 224.02
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "VAT-GEN"
                        },
                        "printPriority": 1000,
                        "description": "          Generation",
                        "rate": null,
                        "amount": 1302.13
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "VAT-TRX"
                        },
                        "printPriority": 1010,
                        "description": "          Transmission",
                        "rate": null,
                        "amount": 211.47
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "VAT-SYS"
                        },
                        "printPriority": 1020,
                        "description": "          System Loss",
                        "rate": null,
                        "amount": 128.65
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "VAT-DIS"
                        },
                        "printPriority": 1030,
                        "description": "          Distribution",
                        "rate": null,
                        "amount": 932.98
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "VAT-OTH"
                        },
                        "printPriority": 1040,
                        "description": "          Others",
                        "rate": null,
                        "amount": 54.69
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "vVATTOT"
                        },
                        "printPriority": 1050,
                        "description": "     Total VAT",
                        "rate": null,
                        "amount": 2629.92
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "NPC_ADJKWH"
                        },
                        "printPriority": 488,
                        "description": "     NPC/PSALM Adjustment",
                        "rate": "-0.1744/kWh",
                        "amount": -575.35
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "RESETCOST-KWH"
                        },
                        "printPriority": 307,
                        "description": "     Reset Cost Adjustment",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "UC-ME-SPUG"
                        },
                        "printPriority": 1105,
                        "description": "          Missionary Electrification NPC-SPUG",
                        "rate": "0.1544/kWh",
                        "amount": 509.37
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "UC-ME-RED"
                        },
                        "printPriority": 1106,
                        "description": "          Missionary Electrification RE Developer",
                        "rate": "0.0017/kWh",
                        "amount": 5.61
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "LFTRKWH"
                        },
                        "printPriority": 921,
                        "description": "     Local Franchise Tax Recovery",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "USD"
                        },
                        "printPriority": 1120,
                        "description": "          NPC Stranded Debts",
                        "rate": "0.0428/kWh",
                        "amount": 141.2
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "USC"
                        },
                        "printPriority": 1122,
                        "description": "          NPC Stranded Contract Costs",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "UDSC"
                        },
                        "printPriority": 1124,
                        "description": "          DUs Stranded Contract Costs",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "UETR"
                        },
                        "printPriority": 1126,
                        "description": "          Equalization of Taxes & Royalties",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "RED_OUTAMT"
                        },
                        "printPriority": 1459,
                        "description": "PLEASE PAY YOUR TOTAL BILL",
                        "rate": null,
                        "amount": 32539.88
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772494,
                            "lineCode": "CURBIL"
                        },
                        "printPriority": 1250,
                        "description": "CURRENT BILL - OCTOBER 2020",
                        "rate": null,
                        "amount": 30992.98
                    }
                ],
                "contacts": [
                    {
                        "acctNo": "4925321111",
                        "contactType": "email",
                        "value": "willy.Olanio@digitel.ph"
                    }
                ],
                "uuid": "8a70ce7c-7bf3-44ef-b198-b4376d88d768"
            },
            {
                "tranNo": 43772796,
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
                "billNo": "277155517918",
                "areaCode": "10",
                "readingBatchNo": null,
                "bookNo": 703,
                "oldSeqNo": 0,
                "newSeqNo": 5,
                "crc": "0940300448",
                "acctNo": "2771821111",
                "rateSchedule": "03-C-43",
                "rateScheduleDesc": "Secondary Retail 2.1: Rate Schedule Code 43",
                "customerName": "DIGITEL MOBILE PHILS. INC,",
                "premiseAdd1": "|PRK.3 GONZAGA ST. KM.23",
                "premiseAdd2": " ",
                "premiseAdd3": " ",
                "billingAdd1": "PILIPINO TELEPHONE CORP.",
                "billingAdd2": "Matina Shrine Hills /Pole#0164992",
                "billingAdd3": "  Meter#451922",
                "messageCode": "2047",
                "powerFactorValue": null,
                "billedKwhrCons": 4742,
                "billedDemandCons": null,
                "billedKvarCons": null,
                "overdueAmt": 3640.62,
                "overdueBillCount": 1,
                "billAmt": 44561.19,
                "totalAmtDue": 48201.81,
                "lastPaymentDate": "2020-09-11 00:00:00",
                "lastPaymentAmount": 46247.97,
                "mainSaId": "2771821494",
                "messengerCode": "0006",
                "altBillId": "1020536194",
                "locationCode": "112-I7",
                "lastBillFlg": "N",
                "parMonth": "2020-08-01 00:00:00",
                "parKwhr": 0,
                "completeDate": "2020-10-02 21:43:46",
                "fltConnection": null,
                "fltWattage": null,
                "noBatchPrtSw": "N",
                "ebillOnlySw": "N",
                "extractedOn": "2020-10-03 02:05:07",
                "tin": "215-398-626-000",
                "busActivity": " ",
                "busAdd1": " ",
                "busAdd2": " ",
                "busAdd3": " ",
                "busAdd4": " ",
                "busAdd5": " ",
                "meterDetails": [
                    {
                        "meterDetailPK": {
                            "tranNo": 43772796,
                            "badgeNo": "372469"
                        },
                        "serialNo": "60035807",
                        "poleNo": "0826082",
                        "multiplier": 1,
                        "prevReadingDate": "2020-09-02 07:09:00",
                        "currReadingDate": "2020-10-02 11:42:00",
                        "connLoad": 20000,
                        "prevKwhrRdg": 38081,
                        "currKwhrRdg": 42823,
                        "regKwhrCons": 4742,
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
                "consumptionHistory": null,
                "lineDetails": [
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "FITA-KWH2"
                        },
                        "printPriority": 1127,
                        "description": "     Feed In Tariff Allowance - FIT-All",
                        "rate": "0.0495/kWh",
                        "amount": 234.73
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "R-SLF"
                        },
                        "printPriority": 485,
                        "description": "     Senior Citizen Subsidy",
                        "rate": "0.00003/kWh",
                        "amount": 0.14
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "UEC"
                        },
                        "printPriority": 1110,
                        "description": "          Environmental Charge",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vGOVTOT"
                        },
                        "printPriority": 1130,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": 5281.5
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "NETSPACER"
                        },
                        "printPriority": 1430,
                        "description": null,
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "OVERDUE"
                        },
                        "printPriority": 5,
                        "description": "PREVIOUS BALANCE",
                        "rate": null,
                        "amount": 3640.62
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "SVR"
                        },
                        "printPriority": 275,
                        "description": "     Supply Charge",
                        "rate": "0.2367/kWh",
                        "amount": 1122.43
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "PREVAMTSPACER"
                        },
                        "printPriority": 6,
                        "description": null,
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "CURCHARGES"
                        },
                        "printPriority": 105,
                        "description": "CURRENT CHARGES",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "ADJLPC"
                        },
                        "printPriority": 540,
                        "description": "     Surcharge",
                        "rate": "2% of 2,649.63",
                        "amount": 52.99
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "CCBREDNOTICE"
                        },
                        "printPriority": 1460,
                        "description": "DISCONNECTION/DUE DATE:48 hours from receipt hereof",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "CCBNOTICE1"
                        },
                        "printPriority": 1461,
                        "description": "LAST PAYMENT  -  SEPTEMBER 11, 2020  -  46,247.97",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vGENCHGHDR"
                        },
                        "printPriority": 110,
                        "description": "Generation & Transmission",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "GEN"
                        },
                        "printPriority": 120,
                        "description": "     Generation Charge",
                        "rate": "4.9223/kWh",
                        "amount": 23341.55
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "TRX-KWH"
                        },
                        "printPriority": 140,
                        "description": "     Transmission Charge",
                        "rate": "0.6503/kWh",
                        "amount": 3083.72
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "SYS"
                        },
                        "printPriority": 150,
                        "description": "     System Loss Charge",
                        "rate": "0.456/kWh",
                        "amount": 2162.35
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vGENTRANSTOT"
                        },
                        "printPriority": 190,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": 28587.62
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vDISTREVHDR"
                        },
                        "printPriority": 200,
                        "description": "Distribution Charges",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "DIST"
                        },
                        "printPriority": 210,
                        "description": "     Distribution Charge",
                        "rate": "1.9371/kWh",
                        "amount": 9185.73
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vUNIVCHGHDR"
                        },
                        "printPriority": 1060,
                        "description": "     Universal Charge",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vVATHDR"
                        },
                        "printPriority": 990,
                        "description": "     Value Added Tax",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "MVR"
                        },
                        "printPriority": 280,
                        "description": "     Metering Charge",
                        "rate": "0.1814/kWh",
                        "amount": 860.2
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "MFX"
                        },
                        "printPriority": 290,
                        "description": "     Metering Charge",
                        "rate": "5.00/month",
                        "amount": 5
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vDISTREVTOT"
                        },
                        "printPriority": 310,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": 11173.36
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vOTHHDR"
                        },
                        "printPriority": 460,
                        "description": "Others",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "SLF-C"
                        },
                        "printPriority": 470,
                        "description": "     Lifeline Rate Subsidy",
                        "rate": "0.0617/kWh",
                        "amount": 292.58
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vOTHTOT"
                        },
                        "printPriority": 590,
                        "description": "Sub-Total",
                        "rate": null,
                        "amount": -481.29
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vGOVREVHDR"
                        },
                        "printPriority": 840,
                        "description": "Government Charges",
                        "rate": null,
                        "amount": null
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "FCT"
                        },
                        "printPriority": 920,
                        "description": "     Franchise Tax - Local",
                        "rate": null,
                        "amount": 322.09
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "VAT-GEN"
                        },
                        "printPriority": 1000,
                        "description": "          Generation",
                        "rate": null,
                        "amount": 1871.67
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "VAT-TRX"
                        },
                        "printPriority": 1010,
                        "description": "          Transmission",
                        "rate": null,
                        "amount": 303.96
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "VAT-SYS"
                        },
                        "printPriority": 1020,
                        "description": "          System Loss",
                        "rate": null,
                        "amount": 184.93
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "VAT-DIS"
                        },
                        "printPriority": 1030,
                        "description": "          Distribution",
                        "rate": null,
                        "amount": 1340.8
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "VAT-OTH"
                        },
                        "printPriority": 1040,
                        "description": "          Others",
                        "rate": null,
                        "amount": 80.14
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "vVATTOT"
                        },
                        "printPriority": 1050,
                        "description": "     Total VAT",
                        "rate": null,
                        "amount": 3781.5
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "NPC_ADJKWH"
                        },
                        "printPriority": 488,
                        "description": "     NPC/PSALM Adjustment",
                        "rate": "-0.1744/kWh",
                        "amount": -827
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "RESETCOST-KWH"
                        },
                        "printPriority": 307,
                        "description": "     Reset Cost Adjustment",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "UC-ME-SPUG"
                        },
                        "printPriority": 1105,
                        "description": "          Missionary Electrification NPC-SPUG",
                        "rate": "0.1544/kWh",
                        "amount": 732.16
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "UC-ME-RED"
                        },
                        "printPriority": 1106,
                        "description": "          Missionary Electrification RE Developer",
                        "rate": "0.0017/kWh",
                        "amount": 8.06
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "LFTRKWH"
                        },
                        "printPriority": 921,
                        "description": "     Local Franchise Tax Recovery",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "USD"
                        },
                        "printPriority": 1120,
                        "description": "          NPC Stranded Debts",
                        "rate": "0.0428/kWh",
                        "amount": 202.96
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "USC"
                        },
                        "printPriority": 1122,
                        "description": "          NPC Stranded Contract Costs",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "UDSC"
                        },
                        "printPriority": 1124,
                        "description": "          DUs Stranded Contract Costs",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "UETR"
                        },
                        "printPriority": 1126,
                        "description": "          Equalization of Taxes & Royalties",
                        "rate": "0.00/kWh",
                        "amount": 0
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "RED_OUTAMT"
                        },
                        "printPriority": 1459,
                        "description": "PLEASE PAY YOUR TOTAL BILL",
                        "rate": null,
                        "amount": 48201.81
                    },
                    {
                        "lineDetailPK": {
                            "tranNo": 43772796,
                            "lineCode": "CURBIL"
                        },
                        "printPriority": 1250,
                        "description": "CURRENT BILL - OCTOBER 2020",
                        "rate": null,
                        "amount": 44561.19
                    }
                ],
                "contacts": [
                    {
                        "acctNo": "2771821111",
                        "contactType": "email",
                        "value": "lovell.manguiob@aboitiz.com"
                    }
                ],
                "uuid": "8a70ce7c-7bf3-44ef-b198-b4376d88d768"
            }
        ]
    }
    ```
    </details>


## Subscription Service

This could be under the **Customer Service** domain. This is the one that manages the customer subscriptions. This is also where **Bill Stager** gets list of customers who signed up for eBill Service.