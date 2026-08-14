package com.example.placementmanagement.repository;

import com.example.placementmanagement.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Long>, JpaSpecificationExecutor<Placement> {
    boolean existsByStudentId(Long studentId);
    Optional<Placement> findByStudentId(Long studentId);

    @Query("SELECT AVG(p.packageAmount) FROM Placement p")
    Double findAveragePackage();

    @Query("SELECT MAX(p.packageAmount) FROM Placement p")
    Double findHighestPackage();
}
