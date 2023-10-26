package com.tomasz.dealership.mappers.impl;

import com.tomasz.dealership.domain.DTO.CarDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements Mapper<CarEntity, CarDto> {
    @Override
    public CarDto mapTo(CarEntity carEntity) {
        return CarDto.builder()
                .id(carEntity.getId())
                .name(carEntity.getName())
                .yearOfProduction(carEntity.getYearOfProduction())
                .horsepower(carEntity.getHorsepower())
                .manufacturer(carEntity.getManufacturer())
                .build();
    }

    @Override
    public CarEntity mapFrom(CarDto carDto) {
        return CarEntity.builder()
                .id(carDto.getId())
                .name(carDto.getName())
                .yearOfProduction(carDto.getYearOfProduction())
                .horsepower(carDto.getHorsepower())
                .manufacturer(carDto.getManufacturer())
                .build();
    }
}
