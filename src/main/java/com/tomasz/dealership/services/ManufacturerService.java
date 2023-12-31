package com.tomasz.dealership.services;

import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManufacturerService {
    ManufacturerEntity save(String manufacturerName, ManufacturerEntity manufacturer);

    Optional<ManufacturerEntity> findOne(String manufacturerName);

    Page<ManufacturerEntity> findAll(Pageable pageable);

    Optional<ManufacturerEntity> fullUpdate(String name, ManufacturerEntity manufacturer);

    Optional<ManufacturerEntity> partialUpdate(String name, ManufacturerEntity manufacturer);

    void delete(String manufacturerName);
}
