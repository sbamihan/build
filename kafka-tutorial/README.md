## Commands to play around with Kafka

Since we already have a **Kafka Cluster** running at 172.18.13.12, we'll just connect to that server and play around with it using the following commands.

First, cd to Kafka directory
```bash
cd C:\workspace\kafka-tutorial\kafka_2.12-2.3.0
```

List Topics
```bash
bin\windows\kafka-topics.bat --list --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181
```

Create Topic
```bash
bin\windows\kafka-topics.bat --create --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181 --topic devops --partitions 1 --replication-factor 1
```

Describe Topic
```bash
bin\windows\kafka-topics.bat --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181 --describe --topic devops

# Order not guaranteed
bin\windows\kafka-topics.bat --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181 --describe --topic scheduled-meter-read

# Order not guaranteed
bin\windows\kafka-topics.bat --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181 --describe --topic ondemand-meter-read

# Order guaranteed
bin\windows\kafka-topics.bat --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181 --describe --topic switch-request-topic
```

Produce Message
```bash
bin\windows\kafka-console-producer.bat --broker-list 172.18.13.12:19092,172.18.13.12:29092,172.18.13.12:39092 --topic devops
```

Consume Message
```bash
bin\windows\kafka-console-consumer.bat --bootstrap-server 172.18.13.12:19092,172.18.13.12:29092,172.18.13.12:39092 -topic devops
```

Delete Topic
```bash
bin\windows\kafka-topics.bat --zookeeper 172.18.13.12:12181,172.18.13.12:22181,172.18.13.12:32181 --delete --topic devops
```