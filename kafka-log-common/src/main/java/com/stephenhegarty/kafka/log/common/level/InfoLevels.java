package com.stephenhegarty.kafka.log.common.level;

import java.util.Random;

public enum InfoLevels {

    ERROR("ERROR"),
    WARNING("WARNING"),
    INFO("INFO"),
    DEBUG("DEBUG");

    private String name;

    InfoLevels(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static InfoLevels getRandomLevel()  {
        return values()[new Random().nextInt(values().length)];
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
