package com.example.placementmanagement.dto.request;

import jakarta.validation.constraints.NotNull;

public class ApplicationRequestDto {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    private String remarks;

    public ApplicationRequestDto() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
