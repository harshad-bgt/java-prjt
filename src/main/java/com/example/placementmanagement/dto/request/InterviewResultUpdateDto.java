package com.example.placementmanagement.dto.request;

import com.example.placementmanagement.enums.InterviewResult;
import com.example.placementmanagement.enums.InterviewStatus;
import jakarta.validation.constraints.NotNull;

public class InterviewResultUpdateDto {

    @NotNull(message = "Result is required")
    private InterviewResult result;

    private String feedback;

    private InterviewStatus status;

    public InterviewResultUpdateDto() {
    }

    public InterviewResult getResult() {
        return result;
    }

    public void setResult(InterviewResult result) {
        this.result = result;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }
}
