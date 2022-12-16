# Kafka Log Consumer

A simple java application which consumes `RawLogMessage` records, deserialize it against the confluent
 cloud schema registry. It will read from the following [topics](./kafka-log-common/src/main/java/com/stephenhegarty/kafka/log/common/KsqlOutputTopics.java)
and output the filtered records out to the console.
### Run Locally

Within the repo directory, run the following maven command
```
mvn clean package
```
Then run
```
java -jar target/kafka-log-consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar {WORK_DIR}/resources/kafka.properties 
```