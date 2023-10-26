package com.tomasz.dealership.domain.DTO;

import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    //a data transfer object, so we don't expose
    //entities to the user
    private Long id;

    private String carName;
    private Integer yearOfProduction;
    private Float fuelConsumption;
    private Integer horsepower;
    //enum fuel type
    private ManufacturerEntity manufacturer;
}
