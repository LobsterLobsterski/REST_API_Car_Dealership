package com.tomasz.dealership.repositories;

import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, String>,
        PagingAndSortingRepository<ManufacturerEntity, String> {
}
