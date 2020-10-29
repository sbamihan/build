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
            "tranNo": 44149348,
            "batchCd": "CMBPEXTG",
            "batchNo": 3158,
            "duSetId": 18929,
            "billingBatchNo": "BC10",
            "billColor": "GREEN",
            "courierCode": "P",
            "billType": "R",
            "billMonth": "2020-10-01 00:00:00",
            "billDate": "2020-10-21",
            "dueDate": "2020-11-02 00:00:00",
            "billNo": "002270407257",
            "areaCode": "10",
            "readingBatchNo": null,
            "bookNo": 636,
            "oldSeqNo": 0,
            "newSeqNo": 6160,
            "crc": "0940980630",
            "acctNo": "0029321111",
            "rateSchedule": "05-P-7",
            "rateScheduleDesc": "Secondary Retail 3.1: Rate Schedule Code 7",
            "customerName": "BANGAYAN,ALEXIS LOZANO",
            "premiseAdd1": "INIGO ST BSIDE CLICKER,OBRERO",
            "premiseAdd2": " ",
            "premiseAdd3": " ",
            "billingAdd1": "INIGO ST BSIDE CLICKER,OBRERO",
            "billingAdd2": " ",
            "billingAdd3": "   ",
            "powerFactorValue": null,
            "billedKwhrCons": 1796,
            "billedDemandCons": 16.12,
            "billedKvarCons": 0,
            "overdueAmt": 22456.62,
            "overdueBillCount": 1,
            "billAmt": 22860.28,
            "totalAmtDue": 45316.9,
            "lastPaymentDate": "2020-09-19 00:00:00",
            "lastPaymentAmount": 45623.25,
            "mainSaId": "0029321972",
            "altBillId": "1020876484",
            "parMonth": "2020-08-01 00:00:00",
            "parKwhr": 0,
            "completeDate": "2020-10-21 11:52:22",
            "noBatchPrtSw": "N",
            "ebillOnlySw": "Y",
            "extractedOn": "2020-10-22 06:09:40",
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
                        "tranNo": 44149348,
                        "badgeNo": "533413"
                    },
                    "serialNo": "11673193",
                    "poleNo": "0689753",
                    "multiplier": 40,
                    "prevReadingDate": "2020-09-11 08:27:00",
                    "currReadingDate": "2020-10-05 10:45:00",
                    "connLoad": 40000,
                    "prevKwhrRdg": 43292,
                    "currKwhrRdg": 43317.7,
                    "regKwhrCons": 1028,
                    "prevDemandRdg": 112.451,
                    "currDemandRdg": 112.854,
                    "regDemandCons": 16.12,
                    "prevKvarRdg": 0,
                    "currKvarRdg": 0,
                    "regKvarCons": 0,
                    "meterType": null
                },
                {
                    "meterDetailPK": {
                        "tranNo": 44149348,
                        "badgeNo": "4SG2019094374"
                    },
                    "serialNo": "800002400",
                    "poleNo": "0689753",
                    "multiplier": 40,
                    "prevReadingDate": "2020-10-05 11:00:00",
                    "currReadingDate": "2020-10-12 00:00:00",
                    "connLoad": 40000,
                    "prevKwhrRdg": 0,
                    "currKwhrRdg": 19.2,
                    "regKwhrCons": 768,
                    "prevDemandRdg": 0,
                    "currDemandRdg": 0.267,
                    "regDemandCons": 10.68,
                    "prevKvarRdg": null,
                    "currKvarRdg": null,
                    "regKvarCons": null,
                    "meterType": null
                }
            ],
            "consumptionHistory": [
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-01-11 00:00:00"
                    },
                    "consumption": 5680
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-03-12 00:00:00"
                    },
                    "consumption": 5124
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-04-12 00:00:00"
                    },
                    "consumption": 776
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-10-12 00:00:00"
                    },
                    "consumption": 1796
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-06-12 00:00:00"
                    },
                    "consumption": 84
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-05-13 00:00:00"
                    },
                    "consumption": 0
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-02-11 00:00:00"
                    },
                    "consumption": 5228
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2019-12-12 00:00:00"
                    },
                    "consumption": 6948
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2019-10-12 00:00:00"
                    },
                    "consumption": 7516
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-08-12 00:00:00"
                    },
                    "consumption": 812
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2019-11-12 00:00:00"
                    },
                    "consumption": 7264
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-09-11 00:00:00"
                    },
                    "consumption": 788
                },
                {
                    "consumptionHistoryPK": {
                        "tranNo": 44149348,
                        "rdgDate": "2020-07-12 00:00:00"
                    },
                    "consumption": 788
                }
            ],
            "lineDetails": [
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "FITA-KWH2"
                    },
                    "printPriority": 1127,
                    "description": "     Feed In Tariff Allowance - FIT-All",
                    "rate": "0.0495/kWh",
                    "amount": 88.9
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "R-SLF"
                    },
                    "printPriority": 485,
                    "description": "     Senior Citizen Subsidy",
                    "rate": "0.00003/kWh",
                    "amount": 0.05
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "UEC"
                    },
                    "printPriority": 1110,
                    "description": "          Environmental Charge",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vGOVTOT"
                    },
                    "printPriority": 1130,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 2363.9
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "NETSPACER"
                    },
                    "printPriority": 1430,
                    "description": null,
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "OVERDUE"
                    },
                    "printPriority": 5,
                    "description": "PREVIOUS BALANCE",
                    "rate": null,
                    "amount": 22456.62
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "PREVAMTSPACER"
                    },
                    "printPriority": 6,
                    "description": null,
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "CURCHARGES"
                    },
                    "printPriority": 105,
                    "description": "CURRENT CHARGES",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "CCBNOTICE"
                    },
                    "printPriority": 1460,
                    "description": "Please pay by due date - 11/02/2020",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "CCBNOTICE1"
                    },
                    "printPriority": 1461,
                    "description": "LAST PAYMENT  -  SEPTEMBER 19, 2020  -  45,623.25",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "DIST2"
                    },
                    "printPriority": 211,
                    "description": "     Distribution Charge",
                    "rate": "224.10/kW",
                    "amount": 3612.49
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vGENCHGHDR"
                    },
                    "printPriority": 110,
                    "description": "Generation & Transmission",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "GEN"
                    },
                    "printPriority": 120,
                    "description": "     Generation Charge",
                    "rate": "4.6507/kWh",
                    "amount": 8352.66
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "TRX-KWH"
                    },
                    "printPriority": 140,
                    "description": "     Transmission Charge",
                    "rate": "0.3784/kWh",
                    "amount": 679.61
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "SYS"
                    },
                    "printPriority": 150,
                    "description": "     System Loss Charge",
                    "rate": "0.5002/kWh",
                    "amount": 898.36
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vGENTRANSTOT"
                    },
                    "printPriority": 190,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 14967.16
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vDISTREVHDR"
                    },
                    "printPriority": 200,
                    "description": "Distribution Charges",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "DIST"
                    },
                    "printPriority": 210,
                    "description": "     Distribution Charge",
                    "rate": "0.3413/kWh",
                    "amount": 612.97
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vUNIVCHGHDR"
                    },
                    "printPriority": 1060,
                    "description": "     Universal Charge",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vVATHDR"
                    },
                    "printPriority": 990,
                    "description": "     Value Added Tax",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "MFX"
                    },
                    "printPriority": 290,
                    "description": "     Metering Charge",
                    "rate": "1,147.73/month",
                    "amount": 1147.73
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vDISTREVTOT"
                    },
                    "printPriority": 310,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": 5743.07
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vOTHHDR"
                    },
                    "printPriority": 460,
                    "description": "Others",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "SLF-C"
                    },
                    "printPriority": 470,
                    "description": "     Lifeline Rate Subsidy",
                    "rate": "0.0597/kWh",
                    "amount": 107.22
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vOTHTOT"
                    },
                    "printPriority": 590,
                    "description": "Sub-Total",
                    "rate": null,
                    "amount": -213.85
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vGOVREVHDR"
                    },
                    "printPriority": 840,
                    "description": "Government Charges",
                    "rate": null,
                    "amount": null
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "FCT"
                    },
                    "printPriority": 920,
                    "description": "     Franchise Tax - Local",
                    "rate": null,
                    "amount": 168.07
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "VAT-GEN"
                    },
                    "printPriority": 1000,
                    "description": "          Generation",
                    "rate": null,
                    "amount": 709.96
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "VAT-DIS"
                    },
                    "printPriority": 1030,
                    "description": "          Distribution",
                    "rate": null,
                    "amount": 689.17
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "VAT-OTH"
                    },
                    "printPriority": 1040,
                    "description": "          Others",
                    "rate": null,
                    "amount": 33.04
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "TRX-KW"
                    },
                    "printPriority": 141,
                    "description": "     Transmission Charge",
                    "rate": "312.44/kW",
                    "amount": 5036.53
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "vVATTOT"
                    },
                    "printPriority": 1050,
                    "description": "     Total VAT",
                    "rate": null,
                    "amount": 1749.71
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "VAT-TRXKWH"
                    },
                    "printPriority": 1010,
                    "description": "          Transmission",
                    "rate": null,
                    "amount": 246.59
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "VAT-SYSKWH"
                    },
                    "printPriority": 1020,
                    "description": "          System Loss",
                    "rate": null,
                    "amount": 70.95
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "NPC_ADJKWH"
                    },
                    "printPriority": 488,
                    "description": "     NPC/PSALM Adjustment",
                    "rate": "-0.1788/kWh",
                    "amount": -321.12
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "RESETCOST-KWH"
                    },
                    "printPriority": 307,
                    "description": "     Reset Cost Adjustment",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "UC-ME-SPUG"
                    },
                    "printPriority": 1105,
                    "description": "          Missionary Electrification NPC-SPUG",
                    "rate": "0.1544/kWh",
                    "amount": 277.3
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "UC-ME-RED"
                    },
                    "printPriority": 1106,
                    "description": "          Missionary Electrification RE Developer",
                    "rate": "0.0017/kWh",
                    "amount": 3.05
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "ADJ_INTERCLASSCROSS"
                    },
                    "printPriority": 487,
                    "description": "     Interclass Cross Subsidy Adjustment",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "LFTRKWH"
                    },
                    "printPriority": 921,
                    "description": "     Local Franchise Tax Recovery",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "SFX-FLT"
                    },
                    "printPriority": 276,
                    "description": "     Supply Charge",
                    "rate": "369.88/month",
                    "amount": 369.88
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "USD"
                    },
                    "printPriority": 1120,
                    "description": "          NPC Stranded Debts",
                    "rate": "0.0428/kWh",
                    "amount": 76.87
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "USC"
                    },
                    "printPriority": 1122,
                    "description": "          NPC Stranded Contract Costs",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "UDSC"
                    },
                    "printPriority": 1124,
                    "description": "          DUs Stranded Contract Costs",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "UETR"
                    },
                    "printPriority": 1126,
                    "description": "          Equalization of Taxes & Royalties",
                    "rate": "0.00/kWh",
                    "amount": 0
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "GREEN_OUTAMT"
                    },
                    "printPriority": 1459,
                    "description": "TOTAL BILL",
                    "rate": null,
                    "amount": 45316.9
                },
                {
                    "lineDetailPK": {
                        "tranNo": 44149348,
                        "lineCode": "CURBIL"
                    },
                    "printPriority": 1250,
                    "description": "CURRENT BILL - OCTOBER 2020",
                    "rate": null,
                    "amount": 22860.28
                }
            ],
            "contacts": [
                {
                    "value": "nxsqunoproperties@gmail.com",
                    "statFlg": "20",
                    "primSw": "Y",
                    "contactType": {
                        "typeCode": "EADD",
                        "description": "Email Address"
                    }
                }
            ]
        }
    ]
}
```
</details>