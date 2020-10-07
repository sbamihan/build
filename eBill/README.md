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

    Should call **Subscription Service** at GET `/search/findByTypeCode?typeCode={{typeCode}}` endpoint, where **typeCode** is the code for the type of subscription such as **EBIL** (for eBill), **OUTN** (for Outage Notification), or **NEWS** (for News). The data should be similar to this.

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
    
    Should call **Bill Retriever** at GET `/{{duCode}}/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint, where **duCode** and **batchNo** are values from `BILL-EXTRACTED` event data and **acctNo** is a value from **Subscription Service**.

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