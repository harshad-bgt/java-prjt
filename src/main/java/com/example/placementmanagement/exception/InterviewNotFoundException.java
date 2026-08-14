package com.example.placementmanagement.exception;

public class InterviewNotFoundException extends ResourceNotFoundException {
    public InterviewNotFoundException(Long id) {
        super("Interview not found with ID: " + id);
    }
}
