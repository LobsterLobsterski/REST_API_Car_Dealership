package com.tomasz.dealership.services.impl;

import com.tomasz.dealership.domain.DTO.CarDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.mappers.impl.CarMapper;
import com.tomasz.dealership.repositories.CarRepository;
import com.tomasz.dealership.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public ResponseEntity<CarDto> save(Long id, CarDto carDto) {
        carDto.setId(id);
        CarEntity carEntity = carMapper.mapFrom(carDto);
        CarEntity saved = carRepository.save(carEntity);
        return new ResponseEntity<>(carMapper.mapTo(saved), HttpStatus.CREATED);
    }
}
