package com.example.placementmanagement.dto.response;

import com.example.placementmanagement.enums.CompanyStatus;

public class CompanyResponseDto {

    private Long id;
    private String companyName;
    private String description;
    private String contactPerson;
    private String email;
    private String phone;
    private String website;
    private String location;
    private CompanyStatus status;
    private Integer activeJobsCount;

    public CompanyResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public Integer getActiveJobsCount() {
        return activeJobsCount;
    }

    public void setActiveJobsCount(Integer activeJobsCount) {
        this.activeJobsCount = activeJobsCount;
    }
}
