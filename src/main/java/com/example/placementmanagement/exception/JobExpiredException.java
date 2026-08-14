package com.example.placementmanagement.exception;

public class JobExpiredException extends RuntimeException {
    public JobExpiredException(String message) {
        super(message);
    }
}
