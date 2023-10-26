package com.tomasz.dealership.services;

import com.tomasz.dealership.domain.DTO.CarDto;
import com.tomasz.dealership.domain.Entities.CarEntity;
import org.springframework.http.ResponseEntity;

public interface CarService {
    //acts as a link between the persistence and presentation layers
    //most computation regarding the entity handling should happen here
    //or in the persistence layer
    ResponseEntity<CarDto> save(Long id, CarDto carDto);
}
