const express = require('express')
const bodyParser = require('body-parser');

const app = express()
const port = 3000

var uuid = require('uuid');

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/', function (req, res) {
  res.send('Hello World!')
})

app.post('/events', function (req, res) {
  billExtractedEvent = {
    uuid: uuid.v1(),
    duCode: req.body.duCode,
    batchNo: req.body.batchNo,
    creDttm: new Date()
  }

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