package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.request.CompanyRequestDto;
import com.example.placementmanagement.dto.response.CompanyResponseDto;
import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.enums.CompanyStatus;
import com.example.placementmanagement.exception.CompanyNotFoundException;
import com.example.placementmanagement.exception.DuplicateResourceException;
import com.example.placementmanagement.mapper.CompanyMapper;
import com.example.placementmanagement.repository.CompanyRepository;
import com.example.placementmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyResponseDto registerCompany(CompanyRequestDto dto) {
        if (companyRepository.existsByCompanyName(dto.getCompanyName())) {
            throw new DuplicateResourceException("Company with name '" + dto.getCompanyName() + "' already exists.");
        }

        Company company = CompanyMapper.toEntity(dto);
        Company savedCompany = companyRepository.save(company);
        return CompanyMapper.toDto(savedCompany);
    }

    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new CompanyNotFoundException(id));
        return CompanyMapper.toDto(company);
    }

    @Override
    public CompanyResponseDto updateCompany(Long id, CompanyRequestDto dto) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new CompanyNotFoundException(id));

        if (!company.getCompanyName().equalsIgnoreCase(dto.getCompanyName()) && companyRepository.existsByCompanyName(dto.getCompanyName())) {
            throw new DuplicateResourceException("Company with name '" + dto.getCompanyName() + "' already exists.");
        }

        CompanyMapper.updateEntity(company, dto);
        Company updatedCompany = companyRepository.save(company);
        return CompanyMapper.toDto(updatedCompany);
    }

    @Override
    public void deactivateCompany(Long id) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new CompanyNotFoundException(id));
        company.setStatus(CompanyStatus.INACTIVE);
        companyRepository.save(company);
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies(String search, CompanyStatus status) {
        List<Company> companies;
        if (search != null && !search.trim().isEmpty()) {
            companies = companyRepository.findByCompanyNameContainingIgnoreCase(search.trim());
        } else if (status != null) {
            companies = companyRepository.findByStatus(status);
        } else {
            companies = companyRepository.findAll();
        }

        return companies.stream()
            .map(CompanyMapper::toDto)
            .collect(Collectors.toList());
    }
}
