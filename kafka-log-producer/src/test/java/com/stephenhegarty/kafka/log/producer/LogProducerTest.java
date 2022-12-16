package com.stephenhegarty.kafka.log.producer;

import com.stephenhegarty.kafka.log.RawLogMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.stephenhegarty.kafka.log.common.utils.LogMessageGenerator.generateLogMessageRecord;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
class LogProducerTest {

    private final String TOPIC_NAME = "raw-logs";

    private KafkaProducer kafkaProducer;
    private MockProducer<String, RawLogMessage> mockProducer;

    private void buildMockProducer() {
        this.mockProducer = new MockProducer<String, RawLogMessage>();
    }

    @Test
    @Disabled
    void givenKeyValueWhenSendThenVerifyHistory() throws ExecutionException, InterruptedException {

        buildMockProducer();
        //when

        Future<RecordMetadata> recordMetadataFuture = mockProducer.send(generateLogMessageRecord(TOPIC_NAME));

        //then
        assertTrue(mockProducer.history().size() == 1);
        assertTrue(recordMetadataFuture.get().partition() == 0);
    }
}
