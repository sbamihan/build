const config = require('./knexfile')[process.env.ENV];
const connection = require('knex')(config);

connection.on('query', function(data) {
  console.log('debug', `[QUERY]`, data.sql);
});

connection.on('query-error', function(error) {
  console.log('error', `[ERROR]`, error);
});

module.exports = connection;