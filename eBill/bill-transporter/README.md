# Bill Transporter

This is the one responsible for transporting the staged bills to the 3rd party client over the internet by posting to the client’s API.

The data to be transported to the client should be similar to this.

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