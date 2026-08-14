package com.example.placementmanagement.controller;

import com.example.placementmanagement.dto.request.JobRequestDto;
import com.example.placementmanagement.dto.response.JobResponseDto;
import com.example.placementmanagement.enums.JobStatus;
import com.example.placementmanagement.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Create job opening
    @PostMapping
    public ResponseEntity<JobResponseDto> createJob(@Valid @RequestBody JobRequestDto dto) {
        JobResponseDto response = jobService.createJob(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJobById(@PathVariable Long id) {
        JobResponseDto response = jobService.getJobById(id);
        return ResponseEntity.ok(response);
    }

    // Update job details
    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDto> updateJob(@PathVariable Long id, @Valid @RequestBody JobRequestDto dto) {
        JobResponseDto response = jobService.updateJob(id, dto);
        return ResponseEntity.ok(response);
    }

    // Toggle status (OPEN/CLOSED/CANCELLED)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleJobStatus(@PathVariable Long id, @RequestParam JobStatus status) {
        jobService.toggleJobStatus(id, status);
        return ResponseEntity.ok().build();
    }

    // Delete job (cancels job)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateJob(@PathVariable Long id) {
        jobService.toggleJobStatus(id, JobStatus.CANCELLED);
        return ResponseEntity.noContent().build();
    }

    // Get all jobs
    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getAllJobs(
        @RequestParam(required = false) String search,
        @RequestParam(required = false) JobStatus status
    ) {
        List<JobResponseDto> jobs = jobService.getAllJobs(search, status);
        return ResponseEntity.ok(jobs);
    }
}
