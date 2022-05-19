package com.example.warehousemanagementlufthansa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trucks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "chassisNumber is mandatory")
    @Column(unique=true)
    private String chassisNumber;
    @Column(unique=true)
    private String licensePlate;
}
