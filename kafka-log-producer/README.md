# Kafka Log Producer

A simple java application which generates random `RawLogMessage` records, serialises it against the confluent
 cloud schema registry and push it to input topics housed on confluent cloud.

### Run Locally

Within the repo directory, run the following maven command
```
mvn clean package
```
Then run
```
java -jar target/kafka-log-producer-1.0.0-SNAPSHOT-jar-with-dependencies.jar {WORK_DIR}/resources/kafka.properties {NUMBER OF LOG MESSAGES TO GENERATE e.g 25}  
```