package com.stephenhegarty.kafka.log.common.utils;

import com.stephenhegarty.kafka.log.RawLogMessage;
import com.stephenhegarty.kafka.log.common.level.InfoLevels;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;

public class LogMessageGenerator {


    public static RawLogMessage generateLogMessage() {
        String logId = randomStringGenerator(false, true);
        String infoLevel = InfoLevels.getRandomLevel().getName();
        String logString = randomStringGenerator(true, false);
        return new RawLogMessage(logId, infoLevel, logString);
    }

    public static ProducerRecord<String,RawLogMessage> generateLogMessageRecord(String topicName) {
        RawLogMessage rawLogMessage = generateLogMessage();
        return new ProducerRecord<>(topicName, rawLogMessage.getLogId().toString(), rawLogMessage);
    }

    private static String randomStringGenerator(boolean useLetters, boolean useNumbers) {
        int length = 10;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

}
