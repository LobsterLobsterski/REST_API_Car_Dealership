package com.tomasz.dealership.services;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;

public interface ManufacturerService {
    ManufacturerEntity save(String manufacturerName, ManufacturerEntity manufacturer);
}
