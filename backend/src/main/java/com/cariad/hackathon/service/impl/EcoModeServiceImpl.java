package com.cariad.hackathon.service.impl;

import com.cariad.hackathon.entity.VehicleStatus;
import com.cariad.hackathon.exception.VehicleAlreadyInEcoModeException;
import com.cariad.hackathon.repository.EcoModeRepository;
import com.cariad.hackathon.service.EcoModeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EcoModeServiceImpl implements EcoModeService {

    private EcoModeRepository ecoModeRepository;

    @Override
    public void activateEcoMode() {
        VehicleStatus latest = ecoModeRepository.findTopByOrderByCreatedAtDesc();
        if (latest != null) {
            if (latest.isEcoMode()) {
                throw new VehicleAlreadyInEcoModeException("Vehicle is already in eco mode");
            }
            latest.setPowerLimit(latest.getPowerLimit() - 50);
            latest.setEcoMode(true);
            ecoModeRepository.save(latest);
        }
    }
}
