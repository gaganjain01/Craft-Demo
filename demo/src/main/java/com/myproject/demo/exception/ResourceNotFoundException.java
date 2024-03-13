package com.myproject.demo.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RestException {
    public ResourceNotFoundException(String errorCode, String message) {
        super(HttpStatus.NOT_FOUND, errorCode, message);
    }
}

     //   throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.toString(), ErrorCodes.LRM_DATA_REQUEST_NOT_FOUND.getDescription());



