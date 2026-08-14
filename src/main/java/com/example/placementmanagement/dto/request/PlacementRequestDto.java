package com.example.placementmanagement.dto.request;

import com.example.placementmanagement.enums.PlacementStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class PlacementRequestDto {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "Job role is required")
    private String jobRole;

    @NotNull(message = "Package amount is required")
    @Positive(message = "Package amount must be positive")
    private Double packageAmount;

    private LocalDate joiningDate;

    private PlacementStatus placementStatus = PlacementStatus.OFFERED;

    public PlacementRequestDto() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public Double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public PlacementStatus getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(PlacementStatus placementStatus) {
        this.placementStatus = placementStatus;
    }
}
