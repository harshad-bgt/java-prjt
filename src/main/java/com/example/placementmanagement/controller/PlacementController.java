package com.example.placementmanagement.controller;

import com.example.placementmanagement.dto.request.PlacementRequestDto;
import com.example.placementmanagement.dto.response.PlacementResponseDto;
import com.example.placementmanagement.service.PlacementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    // Create placement offer
    @PostMapping
    public ResponseEntity<PlacementResponseDto> createPlacement(@Valid @RequestBody PlacementRequestDto dto) {
        PlacementResponseDto response = placementService.createPlacement(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get placement by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlacementResponseDto> getPlacementById(@PathVariable Long id) {
        PlacementResponseDto response = placementService.getPlacementById(id);
        return ResponseEntity.ok(response);
    }

    // Get placement by Student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<PlacementResponseDto> getPlacementByStudentId(@PathVariable Long studentId) {
        PlacementResponseDto response = placementService.getPlacementByStudentId(studentId);
        return ResponseEntity.ok(response);
    }

    // Get all placements
    @GetMapping
    public ResponseEntity<List<PlacementResponseDto>> getAllPlacements() {
        List<PlacementResponseDto> placements = placementService.getAllPlacements();
        return ResponseEntity.ok(placements);
    }
}
