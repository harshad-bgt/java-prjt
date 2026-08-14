package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.request.PlacementRequestDto;
import com.example.placementmanagement.dto.response.PlacementResponseDto;
import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.entity.Job;
import com.example.placementmanagement.entity.Placement;
import com.example.placementmanagement.entity.Student;
import com.example.placementmanagement.enums.ApplicationStatus;
import com.example.placementmanagement.enums.PlacementStatus;
import com.example.placementmanagement.exception.*;
import com.example.placementmanagement.mapper.PlacementMapper;
import com.example.placementmanagement.repository.*;
import com.example.placementmanagement.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacementServiceImpl implements PlacementService {

    @Autowired
    private PlacementRepository placementRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public PlacementResponseDto createPlacement(PlacementRequestDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new StudentNotFoundException(dto.getStudentId()));

        Company company = companyRepository.findById(dto.getCompanyId())
            .orElseThrow(() -> new CompanyNotFoundException(dto.getCompanyId()));

        Job job = jobRepository.findById(dto.getJobId())
            .orElseThrow(() -> new JobNotFoundException(dto.getJobId()));

        if (placementRepository.existsByStudentId(dto.getStudentId())) {
            throw new DuplicateResourceException("Placement record already exists for student ID: " + dto.getStudentId());
        }

        // Must have a SELECTED application
        applicationRepository.findByStudentIdAndJobId(dto.getStudentId(), dto.getJobId())
            .filter(app -> app.getStatus() == ApplicationStatus.SELECTED)
            .orElseThrow(() -> new BusinessRuleException("Student must have a SELECTED application status for this job."));

        Placement placement = new Placement();
        placement.setStudent(student);
        placement.setCompany(company);
        placement.setJob(job);
        placement.setJobRole(dto.getJobRole());
        placement.setPackageAmount(dto.getPackageAmount());
        placement.setJoiningDate(dto.getJoiningDate());
        placement.setPlacementStatus(dto.getPlacementStatus() != null ? dto.getPlacementStatus() : PlacementStatus.OFFERED);

        Placement savedPlacement = placementRepository.save(placement);
        return PlacementMapper.toDto(savedPlacement);
    }

    @Override
    public PlacementResponseDto getPlacementById(Long id) {
        Placement placement = placementRepository.findById(id)
            .orElseThrow(() -> new PlacementNotFoundException(id));
        return PlacementMapper.toDto(placement);
    }

    @Override
    public PlacementResponseDto getPlacementByStudentId(Long studentId) {
        Placement placement = placementRepository.findByStudentId(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("No placement record found for student ID: " + studentId));
        return PlacementMapper.toDto(placement);
    }

    @Override
    public List<PlacementResponseDto> getAllPlacements() {
        return placementRepository.findAll()
            .stream()
            .map(PlacementMapper::toDto)
            .collect(Collectors.toList());
    }
}
