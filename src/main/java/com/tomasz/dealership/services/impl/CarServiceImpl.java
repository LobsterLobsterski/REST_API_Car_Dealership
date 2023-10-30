package com.tomasz.dealership.services.impl;

import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.repositories.CarRepository;
import com.tomasz.dealership.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return carRepository.save(carEntity);
    }

    @Override
    public Optional<CarEntity> findOne(Long id) {
        if (!carRepository.existsById(id)){
            return Optional.empty();
        }
        return carRepository.findById(id);
    }

    @Override
    public Page<CarEntity> findAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Optional<CarEntity> fullUpdate(Long id, CarEntity carEntity) {
        if (!carRepository.existsById(id)){
            return Optional.empty();
        }
        carEntity.setId(id);
        CarEntity updated = carRepository.save(carEntity);
        return Optional.of(updated);

    }

}
