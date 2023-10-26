package com.tomasz.dealership.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_seq")
    private Long id;

    //can't have columns of the same name even in different tables
    private String carName;
    private Integer yearOfProduction;
    private Float fuelConsumption;
    private Integer horsepower;
    //enum fuel type

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_name")
    private ManufacturerEntity manufacturer;

}
