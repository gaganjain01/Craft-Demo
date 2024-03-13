package com.myproject.demo.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    /****
     * if an unexpected error occurs while processing a request,
     * you might catch the original exception, wrap it in your custom exception,
     * and then rethrow it to the higher layers while maintaining the original cause.
     * @param httpStatus
     * @param errorCode
     * @param message
     * @param e
     */
    public RestException(HttpStatus httpStatus, String errorCode, String message, Throwable e) {
        super(message, e);
        this.httpStatus = httpStatus;
        this.errorMessage = message;
        this.errorCode = errorCode;
    }

    public RestException(HttpStatus httpStatus, String errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
