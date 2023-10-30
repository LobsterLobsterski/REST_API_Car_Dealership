package com.tomasz.dealership;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;

public class TestDataUtil {

    public static CarEntity createCarEntityA(final ManufacturerEntity manufacturer) {
        return CarEntity.builder()
                .id(1L)
                .carName("Charger")
                .yearOfProduction(1970)
                .fuelConsumption(11.3f)
                .horsepower(400)
                .manufacturer(manufacturer)
                .build();
    }

    public static CarEntity createCarEntityB(final ManufacturerEntity manufacturer) {
        return CarEntity.builder()
                .id(2L)
                .carName("Mustang")
                .yearOfProduction(1969)
                .fuelConsumption(16.3f)
                .horsepower(335)
                .manufacturer(manufacturer)
                .build();
    }


    public static ManufacturerEntity createManufacturerEntityA() {
        return ManufacturerEntity.builder()
                .manufacturerName("Dodge")
                .countryOfOrigin("USA")
                .build();
    }

    public static ManufacturerEntity createManufacturerEntityB() {
        return ManufacturerEntity.builder()
                .manufacturerName("Ford")
                .countryOfOrigin("USA")
                .build();
    }
}
