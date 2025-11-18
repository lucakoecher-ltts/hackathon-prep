package com.cariad.hackathon.controller;

import com.cariad.hackathon.service.EcoModeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/eco-mode")
public class EcoModeInteractionController {

    private EcoModeService ecoModeService;

    @PostMapping("/activate")
    @Operation(summary = "Activate eco mode")
    public ResponseEntity<String> activateEcoMode() {
        ecoModeService.activateEcoMode();
        return ResponseEntity.ok("Eco mode activated - Power limit set to 50%");
    }
}
