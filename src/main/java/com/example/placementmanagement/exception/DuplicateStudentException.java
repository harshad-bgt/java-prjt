package com.example.placementmanagement.exception;

public class DuplicateStudentException extends DuplicateResourceException {
    public DuplicateStudentException(String email) {
        super("A student with email '" + email + "' already exists in the system.");
    }
}
