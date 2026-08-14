package com.example.placementmanagement.repository;

import com.example.placementmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Simple derived query methods
    boolean existsByEmail(String email);
    
    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByDepartmentIgnoreCase(String department);

    List<Student> findByCgpaGreaterThanEqual(Double minCgpa);

    List<Student> findByActive(Boolean active);

    long countByActiveTrue();
}
