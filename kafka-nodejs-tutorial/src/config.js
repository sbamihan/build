module.exports = {
  kafka: {
    TOPIC: 'passenger',
    BROKERS: ['172.18.13.12:19092','172.18.13.12:29092','172.18.13.12:39092'],
    GROUPID: 'covid-tracker-consumer-group',
    CLIENTID: 'sample-kafka-client'
  }
}
