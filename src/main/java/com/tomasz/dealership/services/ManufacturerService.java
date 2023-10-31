package com.tomasz.dealership.services;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;

import java.util.Optional;

public interface ManufacturerService {
    ManufacturerEntity save(String manufacturerName, ManufacturerEntity manufacturer);

    Optional<ManufacturerEntity> findOne(String manufacturerName);
}
