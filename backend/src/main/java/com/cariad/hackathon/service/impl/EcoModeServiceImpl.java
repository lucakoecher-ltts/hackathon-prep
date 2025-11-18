package com.cariad.hackathon.service.impl;

import com.cariad.hackathon.entity.Vehicle;
import com.cariad.hackathon.entity.VehicleConfigStatus;
import com.cariad.hackathon.entity.VehicleEcoModeHistory;
import com.cariad.hackathon.repository.VehicleConfigStatusRepository;
import com.cariad.hackathon.repository.VehicleEcoModeHistoryRepository;
import com.cariad.hackathon.repository.VehicleRepository;
import com.cariad.hackathon.service.EcoModeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EcoModeServiceImpl implements EcoModeService {

    private VehicleEcoModeHistoryRepository vehicleEcoModeHistoryRepository;
    private VehicleConfigStatusRepository vehicleConfigRepository;
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public void activateEcoMode(String vin) {

        Vehicle vehicle = getVehicle(vin);
        VehicleConfigStatus config = vehicleConfigRepository.findAll().stream()
                .filter(c -> c.getVehicle() != null && vin.equals(c.getVehicle().getVin()))
                .findFirst().orElse(null);

        if (config == null) {
            config = new VehicleConfigStatus();
            config.setOriginalPowerLimit(100);
        }

        config.setCurrentPowerLimit(config.getOriginalPowerLimit() * 50 / 100);
        config.setEcoMode(true);

        config.setVehicle(vehicle);
        vehicleConfigRepository.save(config);

        updateHistory(config, true, vehicle);
    }

    private Vehicle getVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findById(vin).orElse(null);
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }
        return vehicle;
    }

    private void updateHistory(VehicleConfigStatus config, boolean isEcoMode, Vehicle vehicle) {
        VehicleEcoModeHistory history = VehicleEcoModeHistory.builder()
                .powerLimit(config.getCurrentPowerLimit())
                .isEcoMode(isEcoMode)
                .build();
        history.setVehicle(vehicle);
        vehicleEcoModeHistoryRepository.save(history);
    }

    @Override
    @Transactional
    public void deactivateEcoMode(String vin) {
        Vehicle vehicle = getVehicle(vin);
        VehicleConfigStatus config = vehicleConfigRepository.findAll().stream()
                .filter(c -> c.getVehicle() != null && vin.equals(c.getVehicle().getVin()))
                .findFirst().orElse(null);

        if (config != null) {
            config.setCurrentPowerLimit(config.getOriginalPowerLimit());
            config.setEcoMode(false);
            config.setVehicle(vehicle);
            vehicleConfigRepository.save(config);

            updateHistory(config, false, vehicle);
        }
    }
}
