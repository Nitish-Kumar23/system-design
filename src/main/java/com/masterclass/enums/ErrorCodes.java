package com.masterclass.enums;

import lombok.Data;
import lombok.Getter;

import java.sql.SQLException;

@Getter
public enum ErrorCodes {

    BOOKING_ALREADY_EXISTS(101,"booking already exists"),

    SEAT_DOES_NOT_EXIST(102,"seat doesn't exist"),

    SEAT_ALREADY_BOOKED(103,"seat already booked"),

    USER_NOT_FOUND(104,"user not found");

    private Integer errorCode;

    private String errorMessage;

    ErrorCodes(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
