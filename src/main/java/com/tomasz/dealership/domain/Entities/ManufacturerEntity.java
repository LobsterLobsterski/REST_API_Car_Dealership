package com.tomasz.dealership.domain.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="manufacturers")
public class ManufacturerEntity {
    @Id
    private String manufacturerName;
    private String countryOfOrigin;
}
