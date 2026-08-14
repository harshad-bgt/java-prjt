package com.example.placementmanagement.exception;

public class StudentNotFoundException extends ResourceNotFoundException {
    public StudentNotFoundException(Long id) {
        super("Student not found with ID: " + id);
    }
    public StudentNotFoundException(String email) {
        super("Student not found with email: " + email);
    }
}
