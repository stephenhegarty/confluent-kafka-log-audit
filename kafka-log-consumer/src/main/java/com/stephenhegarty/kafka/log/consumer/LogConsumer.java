package com.stephenhegarty.kafka.log.consumer;

import com.stephenhegarty.kafka.log.RawLogMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static com.stephenhegarty.kafka.log.common.topics.output.OutputTopics.DEBUG_TOPIC;
import static com.stephenhegarty.kafka.log.common.topics.output.OutputTopics.ERROR_TOPIC;
import static com.stephenhegarty.kafka.log.common.topics.output.OutputTopics.INFO_TOPIC;
import static com.stephenhegarty.kafka.log.common.topics.output.OutputTopics.WARNING_TOPIC;
import static com.stephenhegarty.kafka.log.common.utils.ConfigLoader.loadConfig;

public class LogConsumer {

    private static final Logger logger = LoggerFactory.getLogger(LogConsumer.class);

    private static final int CONSUMER_POLL_TIME_MS = 100;

    public static void main(final String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Please provide the configuration file path as a command line argument");
            System.exit(1);
        }

        logger.debug("Loading props..");
        final Properties props = loadConfig(args[0]);

        // Start Kafka Consumer and poll
        try (final Consumer<String, RawLogMessage> consumer = new KafkaConsumer<>(props)) {
            //Subscribe to KSQL generated output topics
            consumer.subscribe(Arrays.asList(ERROR_TOPIC.getName(), WARNING_TOPIC.getName(), INFO_TOPIC.getName(), DEBUG_TOPIC.getName()));

            while (true) {
                ConsumerRecords<String, RawLogMessage> records = consumer.poll(Duration.ofMillis(CONSUMER_POLL_TIME_MS));
                for (ConsumerRecord<String, RawLogMessage> record : records) {
                    logger.info("Consumed event from topic {}: value = {}: offset = {}: partition: {}", record.topic(),
                            record.value(),
                            record.offset(),
                            record.partition());
                }
            }
        }
    }
}