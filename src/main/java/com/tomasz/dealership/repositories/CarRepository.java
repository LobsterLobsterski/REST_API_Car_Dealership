package com.tomasz.dealership.repositories;

import com.tomasz.dealership.domain.Entities.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {
    //exposes basic crud functionalities of the persistence layer
}
