package com.example.placementmanagement.exception;

public class ApplicationNotFoundException extends ResourceNotFoundException {
    public ApplicationNotFoundException(Long id) {
        super("Job application not found with ID: " + id);
    }
}
