package com.example.placementmanagement.repository;

import com.example.placementmanagement.entity.Job;
import com.example.placementmanagement.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByJobTitleContainingIgnoreCase(String title);

    List<Job> findByStatus(JobStatus status);

    List<Job> findByMinCgpaLessThanEqual(Double studentCgpa);

    long countByStatus(JobStatus status);
}
