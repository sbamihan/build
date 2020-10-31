require('dotenv').config({silent:true});

const express = require('express');

const app = express();

// Setup middleware for parsing application/json
app.use(express.json());
// Setup middleware for parsing application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

const bill = require('./routes/billRoute');

app.use('/', bill);

app.listen(process.env.PORT, function(){
  obj = {
    col1: Number(1234.567.toFixed(2)),
    col2: 1234.567.toFixed(3)*1,
    col3: 1.005.toFixed(2),
    col4: Number(1.555.toFixed(2)),
    col5: Math.round((1.555) * 100, 2) / 100,
    col6: Math.round(1.555, 2)
  }
  console.log(obj);

  console.log('Listening @ http://localhost:' + process.env.PORT);
});
