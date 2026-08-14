package com.example.placementmanagement.dto.request;

import com.example.placementmanagement.enums.InterviewType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class InterviewRequestDto {

    @NotNull(message = "Application ID is required")
    private Long applicationId;

    @NotNull(message = "Round number is required")
    @Min(value = 1, message = "Round number must be at least 1")
    private Integer roundNumber;

    @NotNull(message = "Interview type is required")
    private InterviewType interviewType;

    @NotNull(message = "Scheduled date and time is required")
    @Future(message = "Scheduled interview time must be in the future")
    private LocalDateTime scheduledDateTime;

    private String interviewerName;

    public InterviewRequestDto() {
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public InterviewType getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(InterviewType interviewType) {
        this.interviewType = interviewType;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }
}
