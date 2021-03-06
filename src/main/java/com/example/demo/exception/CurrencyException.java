package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CurrencyException {
    private final String message;
    private final HttpStatus httpStatus;

    public CurrencyException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
