package com.tomasz.dealership.controllers;

import com.tomasz.dealership.domain.DTO.ManufacturerDto;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.mappers.impl.ManufacturerMapper;
import com.tomasz.dealership.services.ManufacturerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ManufacturerController {

    private ManufacturerService manufacturerService;
    private ManufacturerMapper manufacturerMapper;

    public ManufacturerController(ManufacturerService manufacturerService, ManufacturerMapper manufacturerMapper) {
        this.manufacturerService = manufacturerService;
        this.manufacturerMapper = manufacturerMapper;
    }

    @PostMapping(path = "/manufacturers/{manufacturerName}")
    public ResponseEntity<ManufacturerDto> createUpdateManufacturer(@PathVariable String manufacturerName, @RequestBody ManufacturerDto manufacturer){
        ManufacturerEntity manufacturerEntity = manufacturerMapper.mapFrom(manufacturer);
        ResponseEntity<ManufacturerEntity> response = manufacturerService.saveUpdate(manufacturerName, manufacturerEntity);
        return new ResponseEntity<>(manufacturerMapper.mapTo(response.getBody()), response.getStatusCode());
    }

    @GetMapping(path = "/manufacturers/{manufacturerName}")
    public ResponseEntity<ManufacturerDto> findOneManufacturer(@PathVariable String manufacturerName){
        Optional<ManufacturerEntity> result = manufacturerService.findOne(manufacturerName);
        if (result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(manufacturerMapper.mapTo(result.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/manufacturers")
    public Page<ManufacturerDto> findAllManufacturers(Pageable pageable){
        Page<ManufacturerEntity> result = manufacturerService.findAll(pageable);
        return result.map(manufacturerMapper::mapTo);
    }


    
}
