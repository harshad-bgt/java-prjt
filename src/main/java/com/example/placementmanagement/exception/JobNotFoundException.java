package com.example.placementmanagement.exception;

public class JobNotFoundException extends ResourceNotFoundException {
    public JobNotFoundException(Long id) {
        super("Job opening not found with ID: " + id);
    }
}
