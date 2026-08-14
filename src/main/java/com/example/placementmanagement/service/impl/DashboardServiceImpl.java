package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.response.DashboardSummaryDto;
import com.example.placementmanagement.enums.ApplicationStatus;
import com.example.placementmanagement.enums.CompanyStatus;
import com.example.placementmanagement.enums.JobStatus;
import com.example.placementmanagement.repository.*;
import com.example.placementmanagement.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final PlacementRepository placementRepository;

    @Autowired
    public DashboardServiceImpl(
        StudentRepository studentRepository,
        CompanyRepository companyRepository,
        JobRepository jobRepository,
        ApplicationRepository applicationRepository,
        PlacementRepository placementRepository
    ) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.placementRepository = placementRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardSummaryDto getDashboardSummary() {
        long totalStudents = studentRepository.count();
        long activeCompanies = companyRepository.countByStatus(CompanyStatus.ACTIVE);
        long openJobs = jobRepository.countByStatus(JobStatus.OPEN);
        long totalApplications = applicationRepository.count();
        long shortlistedStudents = applicationRepository.countByStatus(ApplicationStatus.SHORTLISTED);
        long selectedStudents = applicationRepository.countByStatus(ApplicationStatus.SELECTED);
        long placedStudents = placementRepository.count();

        double placementRate = totalStudents > 0 ? ((double) placedStudents / totalStudents) * 100.0 : 0.0;
        Double avgPackageObj = placementRepository.findAveragePackage();
        double avgPackage = avgPackageObj != null ? avgPackageObj : 0.0;

        Double maxPackageObj = placementRepository.findHighestPackage();
        double maxPackage = maxPackageObj != null ? maxPackageObj : 0.0;

        DashboardSummaryDto dto = new DashboardSummaryDto();
        dto.setTotalStudents(totalStudents);
        dto.setActiveCompanies(activeCompanies);
        dto.setOpenJobs(openJobs);
        dto.setTotalApplications(totalApplications);
        dto.setShortlistedStudents(shortlistedStudents);
        dto.setSelectedStudents(selectedStudents);
        dto.setPlacedStudents(placedStudents);
        dto.setPlacementRatePercentage(Math.round(placementRate * 100.0) / 100.0);
        dto.setAveragePackage(Math.round(avgPackage * 100.0) / 100.0);
        dto.setHighestPackage(Math.round(maxPackage * 100.0) / 100.0);

        return dto;
    }
}
