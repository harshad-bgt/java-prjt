package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.ApplicationRequestDto;
import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.entity.Job;
import com.example.placementmanagement.entity.Student;
import com.example.placementmanagement.enums.JobStatus;
import com.example.placementmanagement.exception.DuplicateApplicationException;
import com.example.placementmanagement.exception.JobExpiredException;
import com.example.placementmanagement.exception.StudentNotEligibleException;
import com.example.placementmanagement.repository.ApplicationRepository;
import com.example.placementmanagement.repository.JobRepository;
import com.example.placementmanagement.repository.StudentRepository;
import com.example.placementmanagement.service.impl.ApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    private Student eligibleStudent;
    private Student lowCgpaStudent;
    private Job openJob;
    private ApplicationRequestDto requestDto;

    @BeforeEach
    void setUp() {
        eligibleStudent = new Student();
        eligibleStudent.setId(1L);
        eligibleStudent.setName("Aman Kumar");
        eligibleStudent.setCgpa(8.5);
        eligibleStudent.setActive(true);

        lowCgpaStudent = new Student();
        lowCgpaStudent.setId(2L);
        lowCgpaStudent.setName("Priya Verma");
        lowCgpaStudent.setCgpa(6.0);
        lowCgpaStudent.setActive(true);

        Company company = new Company();
        company.setId(1L);
        company.setCompanyName("Google India");

        openJob = new Job();
        openJob.setId(10L);
        openJob.setCompany(company);
        openJob.setJobTitle("Software Engineer");
        openJob.setMinCgpa(7.5);
        openJob.setStatus(JobStatus.OPEN);
        openJob.setApplicationDeadline(LocalDateTime.now().plusDays(10));

        requestDto = new ApplicationRequestDto();
        requestDto.setStudentId(1L);
        requestDto.setJobId(10L);
    }

    @Test
    @DisplayName("Should throw StudentNotEligibleException when student CGPA is below job min CGPA")
    void applyForJob_LowCgpa_ThrowsException() {
        requestDto.setStudentId(2L);
        when(studentRepository.findById(2L)).thenReturn(Optional.of(lowCgpaStudent));
        when(jobRepository.findById(10L)).thenReturn(Optional.of(openJob));

        assertThrows(StudentNotEligibleException.class, () -> applicationService.applyForJob(requestDto));
        verify(applicationRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw JobExpiredException when application deadline has passed")
    void applyForJob_ExpiredDeadline_ThrowsException() {
        openJob.setApplicationDeadline(LocalDateTime.now().minusDays(1));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(eligibleStudent));
        when(jobRepository.findById(10L)).thenReturn(Optional.of(openJob));

        assertThrows(JobExpiredException.class, () -> applicationService.applyForJob(requestDto));
        verify(applicationRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw DuplicateApplicationException when student already applied for the job")
    void applyForJob_DuplicateApplication_ThrowsException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(eligibleStudent));
        when(jobRepository.findById(10L)).thenReturn(Optional.of(openJob));
        when(applicationRepository.existsByStudentIdAndJobId(1L, 10L)).thenReturn(true);

        assertThrows(DuplicateApplicationException.class, () -> applicationService.applyForJob(requestDto));
        verify(applicationRepository, never()).save(any());
    }
}
