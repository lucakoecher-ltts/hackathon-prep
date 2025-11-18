package com.cariad.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleConfigStatus {

    @Id
    @SequenceGenerator(
            name = "vehicle_config_id__sequence",
            sequenceName = "vehicle_config_id__sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_config_id__sequence"
    )
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "vin")
    private Vehicle vehicle;

    private Integer originalPowerLimit;

    private Integer currentPowerLimit;

    private boolean isEcoMode;
}
