package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.request.JobRequestDto;
import com.example.placementmanagement.dto.response.JobResponseDto;
import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.entity.Job;
import com.example.placementmanagement.entity.Skill;
import com.example.placementmanagement.enums.CompanyStatus;
import com.example.placementmanagement.enums.JobStatus;
import com.example.placementmanagement.exception.BusinessRuleException;
import com.example.placementmanagement.exception.CompanyNotFoundException;
import com.example.placementmanagement.exception.JobNotFoundException;
import com.example.placementmanagement.mapper.JobMapper;
import com.example.placementmanagement.repository.CompanyRepository;
import com.example.placementmanagement.repository.JobRepository;
import com.example.placementmanagement.repository.SkillRepository;
import com.example.placementmanagement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public JobResponseDto createJob(JobRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
            .orElseThrow(() -> new CompanyNotFoundException(dto.getCompanyId()));

        if (company.getStatus() == CompanyStatus.INACTIVE) {
            throw new BusinessRuleException("Cannot create a job opening for an inactive company.");
        }

        Set<Skill> skillSet = new HashSet<>();
        if (dto.getRequiredSkills() != null) {
            for (String skillName : dto.getRequiredSkills()) {
                Skill skill = skillRepository.findByNameIgnoreCase(skillName.trim())
                    .orElseGet(() -> skillRepository.save(new Skill(skillName.trim())));
                skillSet.add(skill);
            }
        }

        Job job = JobMapper.toEntity(dto, company, skillSet);
        Job savedJob = jobRepository.save(job);
        return JobMapper.toDto(savedJob);
    }

    @Override
    public JobResponseDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
            .orElseThrow(() -> new JobNotFoundException(id));
        return JobMapper.toDto(job);
    }

    @Override
    public JobResponseDto updateJob(Long id, JobRequestDto dto) {
        Job job = jobRepository.findById(id)
            .orElseThrow(() -> new JobNotFoundException(id));

        Company company = companyRepository.findById(dto.getCompanyId())
            .orElseThrow(() -> new CompanyNotFoundException(dto.getCompanyId()));

        if (company.getStatus() == CompanyStatus.INACTIVE) {
            throw new BusinessRuleException("Cannot update job opening under an inactive company.");
        }

        Set<Skill> skillSet = new HashSet<>();
        if (dto.getRequiredSkills() != null) {
            for (String skillName : dto.getRequiredSkills()) {
                Skill skill = skillRepository.findByNameIgnoreCase(skillName.trim())
                    .orElseGet(() -> skillRepository.save(new Skill(skillName.trim())));
                skillSet.add(skill);
            }
        }

        JobMapper.updateEntity(job, dto, company, skillSet);
        Job updatedJob = jobRepository.save(job);
        return JobMapper.toDto(updatedJob);
    }

    @Override
    public void toggleJobStatus(Long id, JobStatus status) {
        Job job = jobRepository.findById(id)
            .orElseThrow(() -> new JobNotFoundException(id));
        job.setStatus(status);
        jobRepository.save(job);
    }

    @Override
    public List<JobResponseDto> getAllJobs(String search, JobStatus status) {
        List<Job> jobs;
        if (search != null && !search.trim().isEmpty()) {
            jobs = jobRepository.findByJobTitleContainingIgnoreCase(search.trim());
        } else if (status != null) {
            jobs = jobRepository.findByStatus(status);
        } else {
            jobs = jobRepository.findAll();
        }

        return jobs.stream()
            .map(JobMapper::toDto)
            .collect(Collectors.toList());
    }
}
