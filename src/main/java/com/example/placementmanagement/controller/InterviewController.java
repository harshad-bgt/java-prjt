package com.example.placementmanagement.controller;

import com.example.placementmanagement.dto.request.InterviewRequestDto;
import com.example.placementmanagement.dto.request.InterviewResultUpdateDto;
import com.example.placementmanagement.dto.response.InterviewResponseDto;
import com.example.placementmanagement.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    // Schedule an interview
    @PostMapping
    public ResponseEntity<InterviewResponseDto> scheduleInterview(@Valid @RequestBody InterviewRequestDto dto) {
        InterviewResponseDto response = interviewService.scheduleInterview(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update result/feedback
    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponseDto> updateInterviewResult(
        @PathVariable Long id,
        @Valid @RequestBody InterviewResultUpdateDto dto
    ) {
        InterviewResponseDto response = interviewService.updateInterviewResult(id, dto);
        return ResponseEntity.ok(response);
    }

    // Get interviews for a specific application
    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<InterviewResponseDto>> getInterviewsByApplication(@PathVariable Long applicationId) {
        List<InterviewResponseDto> response = interviewService.getInterviewsByApplication(applicationId);
        return ResponseEntity.ok(response);
    }

    // Get all interviews
    @GetMapping
    public ResponseEntity<List<InterviewResponseDto>> getAllInterviews() {
        List<InterviewResponseDto> response = interviewService.getAllInterviews();
        return ResponseEntity.ok(response);
    }
}
