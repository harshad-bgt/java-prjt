package com.example.placementmanagement.repository;

import com.example.placementmanagement.entity.Application;
import com.example.placementmanagement.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);

    Optional<Application> findByStudentIdAndJobId(Long studentId, Long jobId);

    List<Application> findByStudentId(Long studentId);

    List<Application> findByJobId(Long jobId);

    List<Application> findByStatus(ApplicationStatus status);

    long countByStatus(ApplicationStatus status);
}
