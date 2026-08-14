package com.example.placementmanagement.exception;

public class CompanyNotFoundException extends ResourceNotFoundException {
    public CompanyNotFoundException(Long id) {
        super("Company not found with ID: " + id);
    }
}
