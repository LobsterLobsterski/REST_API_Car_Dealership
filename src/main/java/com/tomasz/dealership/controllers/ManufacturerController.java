package com.tomasz.dealership.controllers;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.mappers.impl.ManufacturerMapper;
import com.tomasz.dealership.services.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManufacturerController {

    private ManufacturerService manufacturerService;
    private ManufacturerMapper manufacturerMapper;

    public ManufacturerController(ManufacturerService manufacturerService, ManufacturerMapper manufacturerMapper) {
        this.manufacturerService = manufacturerService;
        this.manufacturerMapper = manufacturerMapper;
    }

    @PostMapping(path = "/manufacturers/{manufacturerName}")
    public ResponseEntity<ManufacturerDto> createManufacturer(@PathVariable String manufacturerName, @RequestBody ManufacturerDto manufacturer){
        ManufacturerEntity manufacturerEntity = manufacturerMapper.mapFrom(manufacturer);
        ManufacturerEntity saved = manufacturerService.save(manufacturerName, manufacturerEntity);
        return new ResponseEntity<>(manufacturerMapper.mapTo(saved), HttpStatus.CREATED);
    }
}
