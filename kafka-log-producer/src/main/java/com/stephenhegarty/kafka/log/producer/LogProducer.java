package com.stephenhegarty.kafka.log.producer;

import com.stephenhegarty.kafka.log.RawLogMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.stephenhegarty.kafka.log.common.topics.input.InputTopics.RAW_LOGS;
import static com.stephenhegarty.kafka.log.common.utils.ConfigLoader.loadConfig;
import static com.stephenhegarty.kafka.log.common.utils.LogMessageGenerator.generateLogMessageRecord;

public class LogProducer {

    private static final Logger logger = LoggerFactory.getLogger(LogProducer.class);

    public static void main(final String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Please provide the configuration file path and number of messages to generate as a command line argument");
            System.exit(1);
        }

        logger.debug("Loading props..");
        final Properties props = loadConfig(args[0]);
        final int numberOfMessagesToGenerate = Integer.parseInt(args[1]);

        logger.info("Number of messages to generate {} ", numberOfMessagesToGenerate);

        try (KafkaProducer<String, RawLogMessage> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < numberOfMessagesToGenerate; i++) {

                ProducerRecord<String, RawLogMessage> record = generateLogMessageRecord(RAW_LOGS.getName());

                RecordMetadata recordMetadata = sendRecord(producer, record);

                logger.info("Message produced, to offset: " + recordMetadata.offset());
                logger.info("Message produced, partition : " + recordMetadata.partition());
            }
            logger.info("{} events were produced to topic {}", numberOfMessagesToGenerate, RAW_LOGS.getName());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("An error occurred whilst producing log messages", e);
        }
    }

    private static RecordMetadata sendRecord(KafkaProducer<String, RawLogMessage> producer, ProducerRecord<String, RawLogMessage> record)
            throws InterruptedException, ExecutionException {
        return producer.send(record, (event, ex) -> {
            if (ex != null) {
                logger.info("Error occurred in producing event to topic {}: key {}: value = {}", record.topic(), record.key(), record.value());
            } else {
                logger.info("Produced event to topic {}: key {}: value = {}", record.topic(), record.key(), record.value());
            }
        }).get();
    }
}