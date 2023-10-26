package com.tomasz.dealership.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long id;

    private String name;
    private Integer yearOfProduction;
    private Float fuelConsumption;
    private Integer horsepower;
    //enum fuel type

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name")
    private ManufacturerEntity manufacturer;

}
