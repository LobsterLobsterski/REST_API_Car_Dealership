package com.tomasz.dealership.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasz.dealership.TestDataUtil;
import com.tomasz.dealership.domain.Entities.CarEntity;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CarControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CarService carService;

    @Autowired
    public CarControllerIntegrationTests(MockMvc mockMvc, CarService carService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.carService = carService;
    }

    @Test
    public void testThatCreateCarSuccessfullyReturnsHttp201Created() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        String carJson = objectMapper.writeValueAsString(carEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateCarSuccessfulyReturnsCreatedCar() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        String carJson = objectMapper.writeValueAsString(carEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carName").value(carEntity.getCarName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.yearOfProduction").value(carEntity.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.yearOfProduction").value(carEntity.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.fuelConsumption").value(carEntity.getFuelConsumption())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.horsepower").value(carEntity.getHorsepower())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.manufacturer").value(manufacturer)
        );
    }
}
