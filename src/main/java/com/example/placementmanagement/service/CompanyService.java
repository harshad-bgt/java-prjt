package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.CompanyRequestDto;
import com.example.placementmanagement.dto.response.CompanyResponseDto;
import com.example.placementmanagement.enums.CompanyStatus;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto registerCompany(CompanyRequestDto dto);
    CompanyResponseDto getCompanyById(Long id);
    CompanyResponseDto updateCompany(Long id, CompanyRequestDto dto);
    void deactivateCompany(Long id);
    List<CompanyResponseDto> getAllCompanies(String search, CompanyStatus status);
}
