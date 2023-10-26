package com.tomasz.dealership.controllers;

import com.tomasz.dealership.mappers.impl.ManufacturerMapper;
import com.tomasz.dealership.services.ManufacturerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManufacturerController {

    private ManufacturerService manufacturerService;
    private ManufacturerMapper manufacturerMapper;

    public ManufacturerController(ManufacturerService manufacturerService, ManufacturerMapper manufacturerMapper) {
        this.manufacturerService = manufacturerService;
        this.manufacturerMapper = manufacturerMapper;
    }

//    @PostMapping("/manufacturers")
//    public ResponseEntity<ManufacturerDto> createManufacturer(){
//
//    }
}
