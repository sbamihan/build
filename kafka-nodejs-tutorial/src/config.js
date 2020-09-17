module.exports = {
  kafka: {
    TOPIC: 'devops',
    BROKERS: ['172.18.13.12:19092','172.18.13.12:29092','172.18.13.12:39092'],
    GROUPID: 'bills-consumer-group',
    CLIENTID: 'sample-kafka-client'
  }
}
