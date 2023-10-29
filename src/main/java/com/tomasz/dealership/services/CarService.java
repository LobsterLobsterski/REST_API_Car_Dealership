package com.tomasz.dealership.services;

import com.tomasz.dealership.domain.Entities.CarEntity;

import java.util.Optional;

public interface CarService {
    //acts as a link between the persistence and presentation layers
    //most computation regarding the entity handling should happen here
    //or in the persistence layer
    CarEntity save(CarEntity carDto);

    Optional<CarEntity> findOne(Long id);
}
