package com.cariad.hackathon.repository;

import com.cariad.hackathon.entity.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoModeRepository extends JpaRepository<VehicleStatus, Integer> {
    
    VehicleStatus findTopByOrderByCreatedAtDesc();
}
