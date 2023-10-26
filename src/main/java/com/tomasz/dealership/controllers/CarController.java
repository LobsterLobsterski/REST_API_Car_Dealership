package com.tomasz.dealership.controllers;

import com.tomasz.dealership.domain.DTO.CarDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.mappers.impl.CarMapper;
import com.tomasz.dealership.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    //create, read one, read many, list based on whatever, update full, update partial, delete

    private CarService carService;
    private CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @PostMapping(path = "/cars")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto){
        System.out.println("gotten car dto:"+carDto.toString());
        CarEntity carEntity = carMapper.mapFrom(carDto);
        System.out.println("converted entity:"+carEntity.toString());
        CarEntity saved = carService.save(carEntity);
        System.out.println("saved entity:"+saved.toString());
        CarDto returnedDto = carMapper.mapTo(saved);
        System.out.println("returned dto:"+returnedDto.toString());
        return new ResponseEntity<>(returnedDto, HttpStatus.CREATED);
    }
}
