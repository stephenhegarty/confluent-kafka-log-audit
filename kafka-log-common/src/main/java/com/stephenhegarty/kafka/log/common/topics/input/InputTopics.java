package com.stephenhegarty.kafka.log.common.topics.input;

public enum InputTopics {

    RAW_LOGS("raw-logs");

    private String name;

    InputTopics(final String name) {
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
