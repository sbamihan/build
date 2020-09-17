const express = require('express')
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 3000;

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const { Kafka } = require('kafkajs')
const config = require('../src/config')

const client = new Kafka({
  brokers: config.kafka.BROKERS,
  clientId: config.kafka.CLIENTID
})

const topic = config.kafka.TOPIC

const producer = client.producer()

app.post('/events', function (req, res) {
  producer.connect()

  payloads = {
    topic: topic,
    messages: [
      { key: 'coronavirus-alert', value: JSON.stringify(req.body) }
    ]
  }
  console.log('payloads=', payloads)

  producer.send(payloads).then(data => {
    res.set('Content-Type', 'application/json');
    res.json({ message: 'event posted to ' + topic + ' topic' });
  });

});

app.listen(port, function () {
  console.log('Listening @ http://localhost:' + port);
});