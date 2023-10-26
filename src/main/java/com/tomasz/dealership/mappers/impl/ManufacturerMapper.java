package com.tomasz.dealership.mappers.impl;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper implements Mapper<ManufacturerEntity, ManufacturerDto> {
    @Override
    public ManufacturerDto mapTo(ManufacturerEntity manufacturerEntity) {
        return ManufacturerDto.builder()
                .name(manufacturerEntity.getName())
                .countryOfOrigin(manufacturerEntity.getCountryOfOrigin())
                .build();
    }

    @Override
    public ManufacturerEntity mapFrom(ManufacturerDto manufacturerDto) {
        return ManufacturerEntity.builder()
                .name(manufacturerDto.getName())
                .countryOfOrigin(manufacturerDto.getCountryOfOrigin())
                .build();
    }
}
