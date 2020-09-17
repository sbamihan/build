# kafka-nodejs-tutorial
# A sample application using Kafka and Node.js

## Run the application

Below is the folder structure:

![](screenshots/folder-structure.png "folder structure")

  ### 1. config.js 
 
   It constains all infrormation to initialise a new Kafka client instance. 
   
   ```javascript
   module.exports = {
    kafka: {
      TOPIC: 'covid19',
      BROKERS: ['172.18.13.12:19092','172.18.13.12:29092','172.18.13.12:39092'],
      GROUPID: 'covid-tracker-consumer-group',
      CLIENTID: 'sample-kafka-client'
    }
   }

   
   ```

  ### 2. consumer.js

  It listens to the Kafka queue, and process every message coming to the queue. 
  
  
  * initialise a Kafa client and consumer instance
  
  ```javascript
  const kafka = new Kafka({
    clientId: config.kafka.CLIENTID,
    brokers: config.kafka.BROKERS
  })

  const topic = config.kafka.TOPIC
  const consumer = kafka.consumer({
    groupId: config.kafka.GROUPID
  })
  ```
  
  * process each message coming to the queue, if it meets the criteria (filterPassengerInfo), it prints the passenger information
    
  ```javascript
    await consumer.run({
    eachMessage: async ({ message }) => {
        try {
          const jsonObj = JSON.parse(message.value.toString())
          let passengerInfo = filterPassengerInfo(jsonObj)
          if (passengerInfo) {
            console.log(
              '******* Alert!!!!! passengerInfo *********',
              passengerInfo
            )
          }
        } catch (error) {
          console.log('err=', error)
        }
      }
    })
  
  ```
    Run the command below to start consumer.
  
  ```
  npm run consumer
  ```
  
 If the producer is running (step 3 below), you will see message coming through, and I will print  message which meets  conditions (has overseas travel history and temperature is great than or equal to 36.9)
  
  ![](screenshots/consumer.png "consumer")
  

  ### 3. public.js

  Here, we create /events endpoint where we can post data to be published in the queue.


  * Initialise a Kafa client and producer instance
  ```javascript
  const client = new Kafka({
    brokers: config.kafka.BROKERS,
    clientId: config.kafka.CLIENTID
  })

  const topic = config.kafka.TOPIC

  const producer = client.producer()

  ```
  
  * I then loop through input.json file, send message every 5 second
  
  ```javascript
    app.post('/events', function (req, res) {
    producer.connect()

    payloads = {
      topic: topic,
      messages: [
        { key: 'devops-message', value: JSON.stringify(req.body) }
      ]
    }
    console.log('payloads=', payloads)

    producer.send(payloads).then(data => {
      res.set('Content-Type', 'application/json');
      res.json({ message: 'event posted to ' + topic });
    });

  });

  app.listen(port, function () {
    console.log('Listening @ http://localhost:' + port);
  });
  ```
  
  Run the command below to start the app.
  
  ```
  npm run public
  ```
  Invoke the /events POST endpoint to publish message to the queue.
  
  ![](screenshots/producer.png "producer")
  
  

  ### 4. package.js
  
  run `npm run consumer` to start Kafka consumer process, and `npm run public` to simulate Kafka producer process

```javascript

    "consumer": "node ./src/consumer.js",
    "public": "node ./routes/public.js",

```


<a name="reference"></a>
## References

[Kafka Nodejs Example with Producers and Consumers](https://www.bennettnotes.com/post/kafka-nodejs-example/)
