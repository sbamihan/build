# Subscription Service

This could be under the **Customer Service** domain. This is the one that manages the customer subscriptions. This is also where [**Bill Stager**](https://github.com/sbamihan/build/tree/master/eBill/bill-stager) gets list of customers who signed up for eBill Service.

This service provides information in this structure.

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