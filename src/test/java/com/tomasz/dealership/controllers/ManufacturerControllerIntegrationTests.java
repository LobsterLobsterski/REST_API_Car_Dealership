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
    public void testThatCreateManufacturerReturnsHttp201() throws Exception {
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
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityA.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatFindOneManufacturerReturnsHttp200WhenExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindOneManufacturerReturnsHttp404WhenDoesntExists() throws Exception {
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
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityA.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatFindAllManufacturersReturnsCorrectManufacturers() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.content[0].countryOfOrigin").value(manufacturerEntityA.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatFullUpdateManufacturerReturnsHttp200WhenExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.put("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateManufacturerReturnsHttp404WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        //manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.put("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateManufacturerReturnsCorrectManufacturer() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.put("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityB.getCountryOfOrigin())
        );
    }
    
    

    @Test
    public void testThatPartialUpdateManufacturerReturnsHttp200WhenExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setManufacturerName(null);
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.patch("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateManufacturerReturnsHttp404WhenDoesntExists() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        //manufacturerService.saveUpdate(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setManufacturerName(null);
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.patch("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)
        ).andExpect(MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatPartialUpdateManufacturerReturnsCorrectManufacturer() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        ManufacturerEntity manufacturerEntityB = TestDataUtil.createManufacturerEntityB();
        manufacturerEntityB.setManufacturerName(null);
        manufacturerEntityB.setCountryOfOrigin("UPDATED");
        String jsonEntity = objectMapper.writeValueAsString(manufacturerEntityB);

        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntity)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(manufacturerEntityA.getManufacturerName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.countryOfOrigin").value(manufacturerEntityB.getCountryOfOrigin())
        );
    }

    @Test
    public void testThatDeleteManufacturerReturnsHttp200() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);


        mockMvc.perform(MockMvcRequestBuilders.delete("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatDeleteManufacturerDeletesmanufacturer() throws Exception {
        ManufacturerEntity manufacturerEntityA = TestDataUtil.createManufacturerEntityA();
        manufacturerService.save(manufacturerEntityA.getManufacturerName(), manufacturerEntityA);

        mockMvc.perform(MockMvcRequestBuilders.delete("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/"+manufacturerEntityA.getManufacturerName())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound()
        );

    }

}
