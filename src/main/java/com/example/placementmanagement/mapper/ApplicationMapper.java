package com.example.placementmanagement.mapper;

import com.example.placementmanagement.dto.response.ApplicationResponseDto;
import com.example.placementmanagement.entity.Application;

public class ApplicationMapper {

    public static ApplicationResponseDto toDto(Application application) {
        if (application == null) return null;
        ApplicationResponseDto dto = new ApplicationResponseDto();
        dto.setId(application.getId());

        if (application.getStudent() != null) {
            dto.setStudentId(application.getStudent().getId());
            dto.setStudentName(application.getStudent().getName());
            dto.setStudentEmail(application.getStudent().getEmail());
            dto.setStudentCgpa(application.getStudent().getCgpa());
            dto.setStudentDepartment(application.getStudent().getDepartment());
        }

        if (application.getJob() != null) {
            dto.setJobId(application.getJob().getId());
            dto.setJobTitle(application.getJob().getJobTitle());

            if (application.getJob().getCompany() != null) {
                dto.setCompanyId(application.getJob().getCompany().getId());
                dto.setCompanyName(application.getJob().getCompany().getCompanyName());
            }
        }

        dto.setStatus(application.getStatus());
        dto.setAppliedAt(application.getAppliedAt());
        dto.setUpdatedAt(application.getUpdatedAt());
        dto.setRemarks(application.getRemarks());
        return dto;
    }
}
