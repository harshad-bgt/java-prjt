package com.example.placementmanagement.repository;

import com.example.placementmanagement.entity.Company;
import com.example.placementmanagement.enums.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCompanyName(String companyName);

    List<Company> findByCompanyNameContainingIgnoreCase(String name);

    List<Company> findByStatus(CompanyStatus status);

    long countByStatus(CompanyStatus status);
}
