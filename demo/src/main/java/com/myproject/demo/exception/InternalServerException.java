package com.myproject.demo.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends RestException {

    public InternalServerException(String errorCode, String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, message);
    }
    public InternalServerException(String errorCode, String message, Throwable e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, message, e);
    }
}
