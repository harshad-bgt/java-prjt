package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.request.ApplicationRequestDto;
import com.example.placementmanagement.dto.request.ApplicationStatusUpdateDto;
import com.example.placementmanagement.dto.response.ApplicationResponseDto;
import com.example.placementmanagement.entity.Application;
import com.example.placementmanagement.entity.Job;
import com.example.placementmanagement.entity.Student;
import com.example.placementmanagement.enums.ApplicationStatus;
import com.example.placementmanagement.enums.JobStatus;
import com.example.placementmanagement.exception.*;
import com.example.placementmanagement.mapper.ApplicationMapper;
import com.example.placementmanagement.repository.ApplicationRepository;
import com.example.placementmanagement.repository.JobRepository;
import com.example.placementmanagement.repository.StudentRepository;
import com.example.placementmanagement.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public ApplicationResponseDto applyForJob(ApplicationRequestDto dto) {
        // Step 1: Find student
        Student student = studentRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new StudentNotFoundException(dto.getStudentId()));

        if (!Boolean.TRUE.equals(student.getActive())) {
            throw new StudentNotEligibleException("Inactive students cannot apply for jobs.");
        }

        // Step 2: Find job opening
        Job job = jobRepository.findById(dto.getJobId())
            .orElseThrow(() -> new JobNotFoundException(dto.getJobId()));

        if (job.getStatus() != JobStatus.OPEN) {
            throw new JobExpiredException("This job opening is not active.");
        }

        if (job.getApplicationDeadline().isBefore(LocalDateTime.now())) {
            throw new JobExpiredException("The application deadline for this job has passed.");
        }

        // Step 3: Check eligibility (CGPA)
        if (student.getCgpa() < job.getMinCgpa()) {
            throw new StudentNotEligibleException("Student CGPA is below the minimum required CGPA for this job.");
        }

        // Step 4: Check duplicate application
        if (applicationRepository.existsByStudentIdAndJobId(dto.getStudentId(), dto.getJobId())) {
            throw new DuplicateApplicationException(dto.getStudentId(), dto.getJobId());
        }

        // Step 5: Save new application
        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setRemarks(dto.getRemarks());

        Application savedApp = applicationRepository.save(application);
        return ApplicationMapper.toDto(savedApp);
    }

    @Override
    public ApplicationResponseDto getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
            .orElseThrow(() -> new ApplicationNotFoundException(id));
        return ApplicationMapper.toDto(application);
    }

    @Override
    public ApplicationResponseDto updateApplicationStatus(Long id, ApplicationStatusUpdateDto dto) {
        Application application = applicationRepository.findById(id)
            .orElseThrow(() -> new ApplicationNotFoundException(id));

        application.setStatus(dto.getStatus());
        if (dto.getRemarks() != null && !dto.getRemarks().trim().isEmpty()) {
            application.setRemarks(dto.getRemarks());
        }

        Application updatedApp = applicationRepository.save(application);
        return ApplicationMapper.toDto(updatedApp);
    }

    @Override
    public List<ApplicationResponseDto> getAllApplications(ApplicationStatus status, Long studentId, Long jobId) {
        List<Application> applications;
        if (status != null) {
            applications = applicationRepository.findByStatus(status);
        } else if (studentId != null) {
            applications = applicationRepository.findByStudentId(studentId);
        } else if (jobId != null) {
            applications = applicationRepository.findByJobId(jobId);
        } else {
            applications = applicationRepository.findAll();
        }

        return applications.stream()
            .map(ApplicationMapper::toDto)
            .collect(Collectors.toList());
    }
}
