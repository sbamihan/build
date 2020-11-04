# eBill 2.0

This `eBill 2.0` is **Aboitiz Power**'s pilot project for implementing [Microservices Architecture](https://martinfowler.com/articles/microservices.html) using [Event Sourcing Pattern](https://www.martinfowler.com/eaaDev/EventSourcing.html).

Here is how it looks in a high level.

<img src="https://github.com/sbamihan/build/blob/master/eBill/diagrams/architecture.png" width="1020"></a>
<br/>

## Flow of Interactions between Components

1. CC&B posts data to [**eBill API**](https://github.com/sbamihan/build/tree/master/eBill/ebill-api) after BPX
2. **eBill API** publishes Bill Extracted event to Kafka
3. [**Bill Stager**](https://github.com/sbamihan/build/tree/master/eBill/bill-stager) reacts to Bill Extracted event
4. **Bill Stager** gets subscribed customers' contact info from [**Subscription Service**](https://github.com/sbamihan/build/tree/master/eBill/subscription-service)
5. **Bill Stager** calls Bill Retriever to retrieve Bill Info from BPX (expects result coming from step 6)
6. [**Bill Retriever**](https://github.com/sbamihan/build/tree/master/eBill/bill-retriever) fetches Bill Info from BPX (result will be sent back to Bill Stager for finishing touches)
7. **Bill Stager** combines Bill Info (result from step 6) and Account's Contact Info (result from step 4) then saves it to MongoDB
8. After saving to MongoDB, **Bill Stager** publishes Bill Staged event to Kafka
9. [**Bill Transporter**](https://github.com/sbamihan/build/tree/master/eBill/bill-transporter) reacts to Bill Staged event
10. **Bill Transporter** gets staged bills from MongoDb
11. **Bill Transporter** sends staged bills to 3rd party client
12. **eBill API** receives delivery status of transported bills from 3rd party client
13. **eBill API** publishes Bill Delivery Status event to Kafka
14. **Bill Transporter** reacts to Bill Delivery Status event store

    ..14. Another application could also probably react to Bill Delivery Status event to update CC&B
    
15. **Bill Transporter** updates delivery status of the bill on its own data store