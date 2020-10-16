module.exports = {
    kafka: {
      TOPIC: 'bill-extracted',
      BROKERS: ['172.18.13.12:19092','172.18.13.12:29092','172.18.13.12:39092'],
      GROUPID: 'bill-extracted-publisher-group',
      CLIENTID: 'ebill-api'
    }
  }