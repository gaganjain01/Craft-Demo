package com.myproject.demo.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends RestException {

    public InvalidInputException(String errorCode, String message) {
        super(HttpStatus.BAD_REQUEST, errorCode, message);
    }

}