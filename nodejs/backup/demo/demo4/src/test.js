const express = require('express')
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express()
const port = 3000

var uuid = require('uuid');

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const { Kafka } = require('kafkajs')
const config = require('./config')

const client = new Kafka({
  brokers: config.kafka.BROKERS,
  clientId: config.kafka.CLIENTID
})

const topic = config.kafka.TOPIC
const producer = client.producer()

app.get('/', function (req, res) {
  res.send('Hello World!')
})

app.post('/events', function (req, res) {
  newObject = {
    name: "name",
    test: "test"
  }

  billExtractedEvent = {
    uuid: uuid.v1(),
    duCode: req.body.duCode,
    batchNo: req.body.batchNo,
    creDttm: new Date(),
    newItem: newObject
  }

  var sendMessage = async () => {
    await producer.connect()
    await producer.send({
      topic: topic,
      messages: [
        { key: 'ebill-event', value: JSON.stringify(billExtractedEvent) }
      ],
    })
    await producer.disconnect()
  }
  
  sendMessage();
  console.log('published event ', billExtractedEvent)

  res.set('Content-Type', 'application/json');
  res.status(202);
  res.json(billExtractedEvent);

})

app.put('/user', function (req, res) {
  res.send('Got a PUT request at /user')
})

app.delete('/user', function (req, res) {
  res.send('Got a DELETE request at /user')
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})