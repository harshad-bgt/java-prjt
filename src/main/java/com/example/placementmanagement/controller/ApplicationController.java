package com.example.placementmanagement.controller;

import com.example.placementmanagement.dto.request.ApplicationRequestDto;
import com.example.placementmanagement.dto.request.ApplicationStatusUpdateDto;
import com.example.placementmanagement.dto.response.ApplicationResponseDto;
import com.example.placementmanagement.enums.ApplicationStatus;
import com.example.placementmanagement.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply for a job
    @PostMapping
    public ResponseEntity<ApplicationResponseDto> applyForJob(@Valid @RequestBody ApplicationRequestDto dto) {
        ApplicationResponseDto response = applicationService.applyForJob(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get application by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDto> getApplicationById(@PathVariable Long id) {
        ApplicationResponseDto response = applicationService.getApplicationById(id);
        return ResponseEntity.ok(response);
    }

    // Update status
    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponseDto> updateApplicationStatus(
        @PathVariable Long id,
        @Valid @RequestBody ApplicationStatusUpdateDto dto
    ) {
        ApplicationResponseDto response = applicationService.updateApplicationStatus(id, dto);
        return ResponseEntity.ok(response);
    }

    // Get all applications
    @GetMapping
    public ResponseEntity<List<ApplicationResponseDto>> getAllApplications(
        @RequestParam(required = false) ApplicationStatus status,
        @RequestParam(required = false) Long studentId,
        @RequestParam(required = false) Long jobId
    ) {
        List<ApplicationResponseDto> applications = applicationService.getAllApplications(status, studentId, jobId);
        return ResponseEntity.ok(applications);
    }
}
