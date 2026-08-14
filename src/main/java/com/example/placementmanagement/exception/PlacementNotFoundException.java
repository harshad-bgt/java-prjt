package com.example.placementmanagement.exception;

public class PlacementNotFoundException extends ResourceNotFoundException {
    public PlacementNotFoundException(Long id) {
        super("Placement record not found with ID: " + id);
    }
}
