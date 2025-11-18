package com.cariad.hackathon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class VehicleStatus {


    @Id
    @SequenceGenerator(
            name = "vehicle_performance_id_sequence",
            sequenceName = "vehicle_performance_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_performance_id_sequence"
    )
    private Long id;

    @NotBlank
    @Max(value = 100, message = "Power higher limit is restricted to 100%")
    @Min(value = 0, message = "Power higher limit is restricted to 100%")
    private Integer powerLimit;

    private boolean isEcoMode;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
