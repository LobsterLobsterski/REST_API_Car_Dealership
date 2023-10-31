package com.tomasz.dealership.services.impl;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.repositories.ManufacturerRepository;
import com.tomasz.dealership.services.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public ManufacturerEntity save(String manufacturerName, ManufacturerEntity manufacturer) {
        manufacturer.setManufacturerName(manufacturerName);
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Optional<ManufacturerEntity> findOne(String manufacturerName) {
        return manufacturerRepository.findById(manufacturerName);
    }
}
