package com.example.placementmanagement.mapper;

import com.example.placementmanagement.dto.request.CompanyRequestDto;
import com.example.placementmanagement.dto.response.CompanyResponseDto;
import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.enums.JobStatus;

public class CompanyMapper {

    public static Company toEntity(CompanyRequestDto dto) {
        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setDescription(dto.getDescription());
        company.setContactPerson(dto.getContactPerson());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setWebsite(dto.getWebsite());
        company.setLocation(dto.getLocation());
        if (dto.getStatus() != null) {
            company.setStatus(dto.getStatus());
        }
        return company;
    }

    public static void updateEntity(Company company, CompanyRequestDto dto) {
        company.setCompanyName(dto.getCompanyName());
        company.setDescription(dto.getDescription());
        company.setContactPerson(dto.getContactPerson());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setWebsite(dto.getWebsite());
        company.setLocation(dto.getLocation());
        if (dto.getStatus() != null) {
            company.setStatus(dto.getStatus());
        }
    }

    public static CompanyResponseDto toDto(Company company) {
        if (company == null) return null;
        CompanyResponseDto dto = new CompanyResponseDto();
        dto.setId(company.getId());
        dto.setCompanyName(company.getCompanyName());
        dto.setDescription(company.getDescription());
        dto.setContactPerson(company.getContactPerson());
        dto.setEmail(company.getEmail());
        dto.setPhone(company.getPhone());
        dto.setWebsite(company.getWebsite());
        dto.setLocation(company.getLocation());
        dto.setStatus(company.getStatus());

        if (company.getJobs() != null) {
            long activeJobs = company.getJobs().stream()
                .filter(job -> job.getStatus() == JobStatus.OPEN)
                .count();
            dto.setActiveJobsCount((int) activeJobs);
        } else {
            dto.setActiveJobsCount(0);
        }
        return dto;
    }
}
