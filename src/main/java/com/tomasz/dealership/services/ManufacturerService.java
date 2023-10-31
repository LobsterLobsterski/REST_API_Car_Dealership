package com.tomasz.dealership.services;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManufacturerService {
    ManufacturerEntity save(String manufacturerName, ManufacturerEntity manufacturer);

    Optional<ManufacturerEntity> findOne(String manufacturerName);

    Page<ManufacturerEntity> findAll(Pageable pageable);
}
