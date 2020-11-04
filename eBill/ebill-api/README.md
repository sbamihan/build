# eBill API

This is the main interface which serves as the entry point for all interactions from the client. External clients (like **Yondu**) and internal clients like (**CC&B**) can interact with this API thru **REST**.

Procedures:
1.	Receives data from Client.

    For CC&B:

    CC&B should be able to POST data through eBill API at `/events/billExtracted` endpoint containing a payload like this. 

    ```json
    {
        "duCode": "dlpc",
        "batchNo": 3107
    }
    ```

    or like this, if per Account.

    ```json
    {
        "duCode": "dlpc",
        "batchNo": 3107,
        "accountId": "1000000001"
    }
    ```

    [Reference](https://stackoverflow.com/questions/49769273/call-restful-api-through-oracle-query) for calling restful API through oracle query.


    For Yondu:

    Yondu should be able to POST data through eBill API at `/events/deliveryStatus` endpoint containing a payload like this.

    ```json
    {
        "transid": 12345678,
        "msisdn": "09991234567",
        "status_code": 0,
        "timestamp": "20201005054614",
        "rcvd_transid": "65a2b726-1441-498e-84ca-58e0e1c80631",
        "short_url": "https://ebill.aboitizpower.com/PDFViewer/Link?q=DY2",
        "long_url": "https://ebill.aboitizpower.com/PDFViewer/Link?q=DY2"
    }
    ```

2.	Publishes event to the event store based on data received from the Client.

    If from CC&B:
    **eBill API** publishes `BILL-EXTRACTED` event to the event store which should contain the original data posted from CC&B plus **UUID** and datetime it was created. The data should be similar to this.

    ```json
    {
        "uuid": "65a2b726-1441-498e-84ca-58e0e1c80631",
        "duCode": "dlpc",
        "batchNo": 3107,
        "creDttm": "2020-10-05T05:46:14.144+00:00"
    }
    ```

    If data is coming from Yondu:
    **eBill API** publishes `BILL-DELIVERY-STATUS` event to the event store which should contain the original **UUID** of the bill, SMS Number to where the PDF URL is sent, and datetime the status was reported. The data should be similar to this.

    ```json
    {
        "uuid": "65a2b726-1441-498e-84ca-58e0e1c80631",
        "recipientSMS": "09991234567",
        "status": "Delivered",
        "creDttm": "2020-10-05T05:46:14.144+00:00"
    }
    ```    