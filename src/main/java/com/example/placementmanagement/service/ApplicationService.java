package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.ApplicationRequestDto;
import com.example.placementmanagement.dto.request.ApplicationStatusUpdateDto;
import com.example.placementmanagement.dto.response.ApplicationResponseDto;
import com.example.placementmanagement.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    ApplicationResponseDto applyForJob(ApplicationRequestDto dto);
    ApplicationResponseDto getApplicationById(Long id);
    ApplicationResponseDto updateApplicationStatus(Long id, ApplicationStatusUpdateDto dto);
    List<ApplicationResponseDto> getAllApplications(ApplicationStatus status, Long studentId, Long jobId);
}
