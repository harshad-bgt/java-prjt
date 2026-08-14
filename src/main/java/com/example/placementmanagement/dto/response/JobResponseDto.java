package com.example.placementmanagement.dto.response;

import com.example.placementmanagement.enums.JobStatus;
import java.time.LocalDateTime;
import java.util.Set;

public class JobResponseDto {

    private Long id;
    private Long companyId;
    private String companyName;
    private String jobTitle;
    private String jobDescription;
    private String eligibilityCriteria;
    private Double minCgpa;
    private Double salaryPackage;
    private String jobLocation;
    private LocalDateTime applicationDeadline;
    private JobStatus status;
    private LocalDateTime createdAt;
    private Set<String> requiredSkills;
    private Integer totalApplicationsCount;

    public JobResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Integer getTotalApplicationsCount() {
        return totalApplicationsCount;
    }

    public void setTotalApplicationsCount(Integer totalApplicationsCount) {
        this.totalApplicationsCount = totalApplicationsCount;
    }
}
