package com.tomasz.dealership.controllers;

import com.tomasz.dealership.domain.DTO.CarDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.mappers.impl.CarMapper;
import com.tomasz.dealership.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        CarEntity carEntity = carMapper.mapFrom(carDto);
        CarEntity saved = carService.save(carEntity);
        CarDto returnedDto = carMapper.mapTo(saved);
        return new ResponseEntity<>(returnedDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/cars/{id}")
    public ResponseEntity<CarDto> getOneCar(@PathVariable Long id){
        Optional<CarEntity> retrieved = carService.findOne(id);
        if (retrieved.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CarDto carDto = carMapper.mapTo(retrieved.get());
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping(path = "/cars")
    public Page<CarDto> getAllCars(Pageable pageable){
        Page<CarEntity> retrieved = carService.findAll(pageable);
        return retrieved.map(carMapper::mapTo);


    }

    @PutMapping(path = "/cars/{id}")
    public ResponseEntity<CarDto> fullUpdate(@PathVariable Long id, @RequestBody CarDto carDto){
        CarEntity carEntity = carMapper.mapFrom(carDto);
        Optional<CarEntity> response = carService.fullUpdate(id, carEntity);

        if (response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carMapper.mapTo(response.get()), HttpStatus.OK);
    }

    @PatchMapping(path = "/cars/{id}")
    ResponseEntity<CarDto> partialUpdate(@PathVariable Long id, @RequestBody CarDto carDto){
        CarEntity carEntity = carMapper.mapFrom(carDto);
        Optional<CarEntity> response = carService.partialUpdate(id, carEntity);

        if (response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carMapper.mapTo(response.get()), HttpStatus.OK);
    }

}
