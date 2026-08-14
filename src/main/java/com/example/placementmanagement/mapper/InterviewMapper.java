package com.example.placementmanagement.mapper;

import com.example.placementmanagement.dto.response.InterviewResponseDto;
import com.example.placementmanagement.entity.Interview;

public class InterviewMapper {

    public static InterviewResponseDto toDto(Interview interview) {
        if (interview == null) return null;
        InterviewResponseDto dto = new InterviewResponseDto();
        dto.setId(interview.getId());

        if (interview.getApplication() != null) {
            dto.setApplicationId(interview.getApplication().getId());
            if (interview.getApplication().getStudent() != null) {
                dto.setStudentName(interview.getApplication().getStudent().getName());
            }
            if (interview.getApplication().getJob() != null) {
                dto.setJobTitle(interview.getApplication().getJob().getJobTitle());
                if (interview.getApplication().getJob().getCompany() != null) {
                    dto.setCompanyName(interview.getApplication().getJob().getCompany().getCompanyName());
                }
            }
        }

        dto.setRoundNumber(interview.getRoundNumber());
        dto.setInterviewType(interview.getInterviewType());
        dto.setScheduledDateTime(interview.getScheduledDateTime());
        dto.setInterviewerName(interview.getInterviewerName());
        dto.setResult(interview.getResult());
        dto.setFeedback(interview.getFeedback());
        dto.setStatus(interview.getStatus());
        return dto;
    }
}
