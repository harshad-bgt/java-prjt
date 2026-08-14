package com.example.placementmanagement.mapper;

import com.example.placementmanagement.dto.request.JobRequestDto;
import com.example.placementmanagement.dto.response.JobResponseDto;
import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.entity.Job;
import com.example.placementmanagement.entity.Skill;

import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {

    public static Job toEntity(JobRequestDto dto, Company company, Set<Skill> skills) {
        Job job = new Job();
        job.setCompany(company);
        job.setJobTitle(dto.getJobTitle());
        job.setJobDescription(dto.getJobDescription());
        job.setEligibilityCriteria(dto.getEligibilityCriteria());
        job.setMinCgpa(dto.getMinCgpa());
        job.setSalaryPackage(dto.getSalaryPackage());
        job.setJobLocation(dto.getJobLocation());
        job.setApplicationDeadline(dto.getApplicationDeadline());
        if (dto.getStatus() != null) {
            job.setStatus(dto.getStatus());
        }
        if (skills != null) {
            job.setRequiredSkills(skills);
        }
        return job;
    }

    public static void updateEntity(Job job, JobRequestDto dto, Company company, Set<Skill> skills) {
        job.setCompany(company);
        job.setJobTitle(dto.getJobTitle());
        job.setJobDescription(dto.getJobDescription());
        job.setEligibilityCriteria(dto.getEligibilityCriteria());
        job.setMinCgpa(dto.getMinCgpa());
        job.setSalaryPackage(dto.getSalaryPackage());
        job.setJobLocation(dto.getJobLocation());
        job.setApplicationDeadline(dto.getApplicationDeadline());
        if (dto.getStatus() != null) {
            job.setStatus(dto.getStatus());
        }
        if (skills != null) {
            job.setRequiredSkills(skills);
        }
    }

    public static JobResponseDto toDto(Job job) {
        if (job == null) return null;
        JobResponseDto dto = new JobResponseDto();
        dto.setId(job.getId());
        if (job.getCompany() != null) {
            dto.setCompanyId(job.getCompany().getId());
            dto.setCompanyName(job.getCompany().getCompanyName());
        }
        dto.setJobTitle(job.getJobTitle());
        dto.setJobDescription(job.getJobDescription());
        dto.setEligibilityCriteria(job.getEligibilityCriteria());
        dto.setMinCgpa(job.getMinCgpa());
        dto.setSalaryPackage(job.getSalaryPackage());
        dto.setJobLocation(job.getJobLocation());
        dto.setApplicationDeadline(job.getApplicationDeadline());
        dto.setStatus(job.getStatus());
        dto.setCreatedAt(job.getCreatedAt());

        if (job.getRequiredSkills() != null) {
            dto.setRequiredSkills(job.getRequiredSkills().stream().map(Skill::getName).collect(Collectors.toSet()));
        }
        if (job.getApplications() != null) {
            dto.setTotalApplicationsCount(job.getApplications().size());
        } else {
            dto.setTotalApplicationsCount(0);
        }
        return dto;
    }
}
