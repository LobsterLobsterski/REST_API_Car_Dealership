package com.tomasz.dealership.services.impl;

import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.repositories.CarRepository;
import com.tomasz.dealership.services.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;


    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarEntity save(CarEntity carEntity) {
//        carEntity.setId(id);
        return carRepository.save(carEntity);
    }

    @Override
    public Optional<CarEntity> findOne(Long id) {
        if (!carRepository.existsById(id)){
            return Optional.empty();
        }
        return carRepository.findById(id);
    }
}
