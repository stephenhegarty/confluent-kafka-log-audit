package com.stephenhegarty.kafka.log.common.topics.output;

public enum OutputTopics {

    ERROR_TOPIC("LOGS_ERROR_FILTERED"),
    WARNING_TOPIC("LOGS_WARNING_FILTERED"),
    DEBUG_TOPIC("LOGS_WARNING_FILTERED"),
    INFO_TOPIC("LOGS_INFO_FILTERED");

    private String name;

    OutputTopics(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
