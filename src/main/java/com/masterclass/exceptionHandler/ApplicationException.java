package com.masterclass.exceptionHandler;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private Integer errorCode;

    private String message;

    public ApplicationException(Integer errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
