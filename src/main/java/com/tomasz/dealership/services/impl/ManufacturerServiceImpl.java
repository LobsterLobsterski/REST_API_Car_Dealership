package com.tomasz.dealership.services.impl;

import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.repositories.ManufacturerRepository;
import com.tomasz.dealership.services.ManufacturerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public ResponseEntity<ManufacturerEntity> saveUpdate(String manufacturerName, ManufacturerEntity manufacturer) {
        manufacturer.setManufacturerName(manufacturerName);
        if (!manufacturerRepository.existsById(manufacturerName)){
            return new ResponseEntity<>(manufacturerRepository.save(manufacturer), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(manufacturerRepository.save(manufacturer), HttpStatus.OK);

    }

    @Override
    public Optional<ManufacturerEntity> findOne(String manufacturerName) {
        return manufacturerRepository.findById(manufacturerName);
    }

    @Override
    public Page<ManufacturerEntity> findAll(Pageable pageable) {
        return manufacturerRepository.findAll(pageable);
    }

    @Override
    public Optional<ManufacturerEntity> fullUpdate(String name, ManufacturerEntity manufacturer) {
        if (!manufacturerRepository.existsById(name)){
            return Optional.empty();
        }
        manufacturer.setManufacturerName(name);
        ManufacturerEntity updated = manufacturerRepository.save(manufacturer);
        return Optional.of(updated);

    }

    @Override
    public Optional<ManufacturerEntity> partialUpdate(String name, ManufacturerEntity manufacturerData) {
        if (!manufacturerRepository.existsById(name)){
            return Optional.empty();
        }
        ManufacturerEntity manufacturer = manufacturerRepository.findById(name).get();
        if (!manufacturerData.getCountryOfOrigin().isBlank()){
            manufacturer.setCountryOfOrigin(manufacturerData.getCountryOfOrigin());
        }
        ManufacturerEntity updated = manufacturerRepository.save(manufacturer);
        updated.setManufacturerName(name);
        return Optional.of(updated);
    }
}
