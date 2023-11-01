package com.tomasz.dealership.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasz.dealership.TestDataUtil;
import com.tomasz.dealership.domain.Entities.ManufacturerEntity;
import com.tomasz.dealership.services.ManufacturerService;
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
public class ManufacturerControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ManufacturerService manufacturerService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ManufacturerControllerIntegrationTests(MockMvc mockMvc, ManufacturerService manufacturerService) {
        this.mockMvc = mockMvc;
        this.manufacturerService = manufacturerService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateManufacturerReturnsHtml201() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateManufacturerReturnsCreatedManufacturer() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityA.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatFindOneManufacturerReturnsHtml200WhenExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindOneManufacturerReturnsHtml404WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        //manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFindOneManufacturerReturnsCorrectManufacturer() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityA.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatFindAllManufacturersReturnsCorrectManufacturers() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.content[0].countryOfOrigin").value(manufacturerEntityA.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatFullUpdateManufacturerReturnsHtml200WhenExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateManufacturerReturnsHtml201WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerEntityA.setManufacturerName("UPDATED");
        //manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);
        manufacturerEntityA.setManufacturerName("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatFullUpdateManufacturerReturnsCorrectManufacturer() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityB.getCountryOfOrigin())
        );
    }
}
