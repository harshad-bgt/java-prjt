package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.JobRequestDto;
import com.example.placementmanagement.dto.response.JobResponseDto;
import com.example.placementmanagement.enums.JobStatus;

import java.util.List;

public interface JobService {
    JobResponseDto createJob(JobRequestDto dto);
    JobResponseDto getJobById(Long id);
    JobResponseDto updateJob(Long id, JobRequestDto dto);
    void toggleJobStatus(Long id, JobStatus status);
    List<JobResponseDto> getAllJobs(String search, JobStatus status);
}
