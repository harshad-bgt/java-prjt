package com.example.placementmanagement.exception;

public class DuplicateApplicationException extends DuplicateResourceException {
    public DuplicateApplicationException(Long studentId, Long jobId) {
        super("Student (ID: " + studentId + ") has already applied for Job (ID: " + jobId + "). Duplicate applications are not allowed.");
    }
}
