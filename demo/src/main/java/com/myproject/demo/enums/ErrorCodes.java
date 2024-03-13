package com.myproject.demo.enums;

public enum ErrorCodes {
    RESOURCE_NOT_FOUND("Resource not found");

    private final String description;

    ErrorCodes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
