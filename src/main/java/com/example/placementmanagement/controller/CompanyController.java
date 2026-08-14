package com.example.placementmanagement.controller;

import com.example.placementmanagement.dto.request.CompanyRequestDto;
import com.example.placementmanagement.dto.response.CompanyResponseDto;
import com.example.placementmanagement.enums.CompanyStatus;
import com.example.placementmanagement.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Register a company
    @PostMapping
    public ResponseEntity<CompanyResponseDto> registerCompany(@Valid @RequestBody CompanyRequestDto dto) {
        CompanyResponseDto response = companyService.registerCompany(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get company by ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long id) {
        CompanyResponseDto response = companyService.getCompanyById(id);
        return ResponseEntity.ok(response);
    }

    // Update company details
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequestDto dto) {
        CompanyResponseDto response = companyService.updateCompany(id, dto);
        return ResponseEntity.ok(response);
    }

    // Deactivate company
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateCompany(@PathVariable Long id) {
        companyService.deactivateCompany(id);
        return ResponseEntity.noContent().build();
    }

    // Get all companies
    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies(
        @RequestParam(required = false) String search,
        @RequestParam(required = false) CompanyStatus status
    ) {
        List<CompanyResponseDto> companies = companyService.getAllCompanies(search, status);
        return ResponseEntity.ok(companies);
    }
}
