package com.bside.server.global.utils.profile;

public enum Profile {
    DEV("dev"),
    PROD("prod");

    private final String value;

    Profile(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
