package com.example.placementmanagement.mapper;

import com.example.placementmanagement.dto.response.PlacementResponseDto;
import com.example.placementmanagement.entity.Placement;

public class PlacementMapper {

    public static PlacementResponseDto toDto(Placement placement) {
        if (placement == null) return null;
        PlacementResponseDto dto = new PlacementResponseDto();
        dto.setId(placement.getId());

        if (placement.getStudent() != null) {
            dto.setStudentId(placement.getStudent().getId());
            dto.setStudentName(placement.getStudent().getName());
            dto.setStudentEmail(placement.getStudent().getEmail());
            dto.setStudentDepartment(placement.getStudent().getDepartment());
        }

        if (placement.getCompany() != null) {
            dto.setCompanyId(placement.getCompany().getId());
            dto.setCompanyName(placement.getCompany().getCompanyName());
        }

        if (placement.getJob() != null) {
            dto.setJobId(placement.getJob().getId());
        }

        dto.setJobRole(placement.getJobRole());
        dto.setPackageAmount(placement.getPackageAmount());
        dto.setJoiningDate(placement.getJoiningDate());
        dto.setPlacementStatus(placement.getPlacementStatus());
        dto.setOfferedAt(placement.getOfferedAt());
        return dto;
    }
}
