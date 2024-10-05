package com.masterclass.enums;

import lombok.Getter;

public enum ErrorCodes {

    BOOKING_ALREADY_EXISTS(101, "booking already exists"),

    SEAT_DOES_NOT_EXIST(102, "seat doesn't exist"),

    SEAT_ALREADY_BOOKED(103, "seat already booked"),

    USER_NOT_FOUND(104, "user not found");

    private final Integer errorCode;

    private final String errorMessage;

    ErrorCodes(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
