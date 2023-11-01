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

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final CarService carService;

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
    public void testThatCreateCarSuccessfullyReturnsCreatedCar() throws Exception {
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

    @Test
    public void testThatGetOneCarSuccessfullyReturnsHttp200OkWhenExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetOneCarSuccessfullyReturnsHttp404WhenDesntExist() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        //carService.save(carEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetOneCarSuccessfullyReturnsACCar() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
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

    @Test
    public void testThatGetAllCarsSuccessfullyReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAllCarsSuccessfullyReturnsCars() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].carName").value(carEntity.getCarName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].yearOfProduction").value(carEntity.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].yearOfProduction").value(carEntity.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].fuelConsumption").value(carEntity.getFuelConsumption())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].horsepower").value(carEntity.getHorsepower())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].manufacturer").value(manufacturer)
        );
    }

    @Test
    public void testThatFullUpdateCarSuccessfullyReturnsHttp200WhenExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        ManufacturerEntity manufacturer2 = TestDataUtil.createManufacturerEntityB();
        CarEntity carEntity2 = TestDataUtil.createCarEntityB(manufacturer2);
        String updatingJson = objectMapper.writeValueAsString(carEntity2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatingJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateCarSuccessfullyReturnsHttp404WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        //carService.save(carEntity);

        ManufacturerEntity manufacturer2 = TestDataUtil.createManufacturerEntityB();
        CarEntity carEntity2 = TestDataUtil.createCarEntityB(manufacturer2);
        String updatingJson = objectMapper.writeValueAsString(carEntity2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatingJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateCarSuccessfullyReturnsUpdatedCar() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        ManufacturerEntity manufacturer2 = TestDataUtil.createManufacturerEntityB();
        CarEntity carEntity2 = TestDataUtil.createCarEntityB(manufacturer2);
        carEntity2.setId(carEntity.getId());
        String updatingJson = objectMapper.writeValueAsString(carEntity2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatingJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carName").value(carEntity2.getCarName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.yearOfProduction").value(carEntity2.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.yearOfProduction").value(carEntity2.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.fuelConsumption").value(carEntity2.getFuelConsumption())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.horsepower").value(carEntity2.getHorsepower())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.manufacturer").value(manufacturer2)
        );
    }

    @Test
    public void testThatPartialUpdateCarSuccessfullyReturnsHttp200WhenExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        CarEntity carEntity2 = TestDataUtil.createCarEntityA(manufacturer);
        carEntity2.setCarName("UPDATED");
        String updatingJson = objectMapper.writeValueAsString(carEntity2);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatingJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateCarSuccessfullyReturnsHttp404WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        //carService.save(carEntity);

        CarEntity carEntity2 = TestDataUtil.createCarEntityA(manufacturer);
        carEntity2.setCarName("UPDATED");
        String updatingJson = objectMapper.writeValueAsString(carEntity2);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatingJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatPartialUpdateCarSuccessfullyReturnsUpdatedCar() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);

        CarEntity carEntity2 = TestDataUtil.createCarEntityA(manufacturer);
        carEntity2.setCarName("UPDATED");
        String updatingJson = objectMapper.writeValueAsString(carEntity2);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatingJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.carName").value(carEntity2.getCarName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.yearOfProduction").value(carEntity2.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.yearOfProduction").value(carEntity2.getYearOfProduction())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.fuelConsumption").value(carEntity2.getFuelConsumption())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.horsepower").value(carEntity2.getHorsepower())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.manufacturer").value(carEntity2.getManufacturer())
        );
    }

    @Test
    public void testThatDeleteCarCarSuccessfullyReturnsHttp204WhenExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);


        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteCarSuccessfullyReturnsHttp204WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        //carService.save(carEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteCarSuccessfullyDeletesCar() throws Exception {
        ManufacturerEntity manufacturer = TestDataUtil.createManufacturerEntityA();
        CarEntity carEntity = TestDataUtil.createCarEntityA(manufacturer);
        carService.save(carEntity);


        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/"+carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

    }
}
