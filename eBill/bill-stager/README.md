# Bill Stager

This is the one responsible for preparing and finalizing the bill information of customers who signed up for eBill Service that are included in the extracted bills.

Procedures:
1.  **Bill Stager** reacts to `BILL-EXTRACTED` event published by [**eBill API**](https://github.com/sbamihan/build/tree/master/eBill/ebill-api).
2.	Gets customer information (Account ID, email) from **Subscription Service**.

    **Bill Stager** should call **Subscription Service** at GET `/{{duCode}}/accounts/search/findByTypeCode?typeCode={{typeCode}}` endpoint, where **duCode** is a value taken from the `BILL-EXTRACTED` event data and **typeCode** is the code for the type of subscription such as **EBIL** (for eBill), **OUTN** (for Outage Notification), or **NEWS** (for News). A sample data structure that this service provides can be found at [Subscription Service](https://github.com/sbamihan/build/tree/master/eBill/subscription-service).

3.	Retrieves bill information of subscribed customers from CC&B through [**Bill Retriever**](https://github.com/sbamihan/build/tree/master/eBill/bill-retriever).
    
    **Bill Stager** should call **Bill Retriever** at GET `/{{duCode}}/bills/findByBatchNoAndAcctNo?batchNo={{batchNo}}&acctNo={{acctNo}}` endpoint, where **duCode** and **batchNo** are values from `BILL-EXTRACTED` event data and **acctNo** is a value from **Subscription Service**.

4.	Projects the bill information then saves to persistence store.

    The projected data should contain the **UUID** from `BILL-EXTRACTED` event data, datetime it was created, and bill information of all accounts who signed up for eBill service that belong on that batch including its contact information taken from **Subscription Service**. The projected data should be similar to this.

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