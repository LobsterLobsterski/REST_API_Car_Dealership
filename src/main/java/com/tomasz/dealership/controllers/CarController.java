package com.tomasz.dealership.controllers;

import com.tomasz.dealership.domain.DTO.CarDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    //create, read one, read many, list based on whatever, update full, update partial, delete

    private CarService carService;

    @PostMapping(path = "/cars/{id}")
    public ResponseEntity<CarDto> createCar(@PathVariable Long id, @RequestBody CarDto carDto){
        return carService.save(id, carDto);
    }
}
