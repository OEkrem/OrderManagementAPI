package com.oekrem.SpringMVCBackEnd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oekrem.SpringMVCBackEnd.models.Address;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc // super powerful way of testing your controllers
public class AddressControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AddressControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAddressSuccessfullyReturnsHttp201Create() throws Exception {
        User testUser = TestDataUtil.createTestUserA();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser))
        ).andExpect(status().isCreated());

        Address address = TestDataUtil.createTestAddressA();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/addresses/users/{id}", address.getUser().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(address))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(address.getName()))
                .andExpect(jsonPath("$.doorNumber").value(address.getDoorNumber()))
                .andExpect(jsonPath("$.floor").value(address.getFloor()))
                .andExpect(jsonPath("$.buildingNumber").value(address.getBuildingNumber()))
                .andExpect(jsonPath("$.street").value(address.getStreet()))
                .andExpect(jsonPath("$.city").value(address.getCity()))
                .andExpect(jsonPath("$.country").value(address.getCountry()));
    }

    @Test
    public void testThatGetAddressSuccessfullyReturnsHttp200Get() throws Exception {

    }

    @Test
    public void testThatGetAddressesSuccessfullyReturnsHttp200Get() throws Exception {

    }

    @Test
    public void testThatUpdateAddressSuccessfullyReturnsHttp200Update() throws Exception {

    }

    @Test
    public void testThatDeleteAddressSuccessfullyReturnsHttp200Update() throws Exception {

    }


}
