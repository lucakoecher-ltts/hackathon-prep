package com.cariad.hackathon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {

    @Id
    @NotBlank(message = "VIN is required")
    private String vin;

    @NotBlank
    private String make;

    @NotBlank
    private String model;
}
