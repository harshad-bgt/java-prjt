package com.example.placementmanagement.dto.request;

import com.example.placementmanagement.enums.JobStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

public class JobRequestDto {

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    private String jobDescription;

    private String eligibilityCriteria;

    @NotNull(message = "Minimum CGPA is required")
    @DecimalMin(value = "0.0", message = "Minimum CGPA cannot be negative")
    @DecimalMax(value = "10.0", message = "Minimum CGPA cannot exceed 10.0")
    private Double minCgpa;

    @NotNull(message = "Salary package is required")
    @Positive(message = "Salary package must be a positive number")
    private Double salaryPackage;

    @NotBlank(message = "Job location is required")
    private String jobLocation;

    @NotNull(message = "Application deadline is required")
    @Future(message = "Application deadline must be in the future")
    private LocalDateTime applicationDeadline;

    private JobStatus status = JobStatus.OPEN;

    private Set<String> requiredSkills;

    public JobRequestDto() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public Double getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(Double minCgpa) {
        this.minCgpa = minCgpa;
    }

    public Double getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(Double salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public LocalDateTime getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDateTime applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
