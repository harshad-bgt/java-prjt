package com.example.placementmanagement.repository;

import com.example.placementmanagement.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long>, JpaSpecificationExecutor<Interview> {
    List<Interview> findByApplicationIdOrderByRoundNumberAsc(Long applicationId);
    boolean existsByApplicationIdAndRoundNumber(Long applicationId, Integer roundNumber);
}
