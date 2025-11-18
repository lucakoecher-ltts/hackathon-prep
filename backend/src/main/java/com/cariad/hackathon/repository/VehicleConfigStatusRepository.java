package com.cariad.hackathon.repository;

import com.cariad.hackathon.entity.VehicleConfigStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleConfigStatusRepository extends JpaRepository<VehicleConfigStatus, Long> {
}