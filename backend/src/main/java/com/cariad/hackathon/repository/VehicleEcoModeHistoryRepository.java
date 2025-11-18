package com.cariad.hackathon.repository;

import com.cariad.hackathon.entity.VehicleEcoModeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleEcoModeHistoryRepository extends JpaRepository<VehicleEcoModeHistory, Long> {

}
