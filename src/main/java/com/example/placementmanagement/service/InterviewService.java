package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.InterviewRequestDto;
import com.example.placementmanagement.dto.request.InterviewResultUpdateDto;
import com.example.placementmanagement.dto.response.InterviewResponseDto;

import java.util.List;

public interface InterviewService {
    InterviewResponseDto scheduleInterview(InterviewRequestDto dto);
    InterviewResponseDto updateInterviewResult(Long id, InterviewResultUpdateDto dto);
    List<InterviewResponseDto> getInterviewsByApplication(Long applicationId);
    List<InterviewResponseDto> getAllInterviews();
}
