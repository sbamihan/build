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
  console.log('Listening @ http://localhost:' + process.env.PORT);
});
