package com.oekrem.SpringMVCBackEnd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class UserControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    public UserControllerIntegrationTests() {}

    @Autowired
    public UserControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateUserSuccessfullyReturnsHttp201Create() throws Exception {
        User testUser = TestDataUtil.createTestUserA();
        testUser.setId(null);
        testUser.setAddresses(null);
        String userJson = objectMapper.writeValueAsString(testUser);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                status().isCreated()
        );
    }

    @Test
    public void testThatCreateUserSuccessfullyReturnsSavedUser() throws Exception {
        User testUser = TestDataUtil.createTestUserA();
        testUser.setId(null);
        testUser.setAddresses(null);
        String userJson = objectMapper.writeValueAsString(testUser);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                jsonPath("$.username").value(testUser.getUsername())
        ).andExpect(
                jsonPath("$.password").value(testUser.getPassword())
        ).andExpect(
                jsonPath("$.firstName").value(testUser.getFirstName())
        ).andExpect(
                jsonPath("$.lastName").value(testUser.getLastName())
        ).andExpect(
                jsonPath("$.email").value(testUser.getEmail())
        ).andExpect(
                jsonPath("$.phone").value(testUser.getPhone())
        );
    }

    @Test
    public void testThatGetUserSuccessfullyReturnsHttp200Get() throws Exception {
        User testUser = TestDataUtil.createTestUserA();
        String userJson = objectMapper.writeValueAsString(testUser);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        );
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())  // HTTP 200 OK yanıtı bekliyoruz
                .andExpect(jsonPath("$.id").value(testUser.getId()))  // ID'nin doğru olduğuna emin oluyoruz
                .andExpect(jsonPath("$.addresses").doesNotExist());
    }

    @Test void testThatGetUsersSuccessfullyReturnsHttp200Get() throws Exception {
        User testUserA = TestDataUtil.createTestUserA();
        User testUserB = TestDataUtil.createTestUserB();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserA))
        );
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserB))
        );
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").value(testUserA.getUsername()))
                .andExpect(jsonPath("$[1].username").value(testUserB.getUsername()));
    }

    @Test
    public void testThatUpdateUserSuccessfullyReturnsHttp200Update() throws Exception {
        User user = TestDataUtil.createTestUserA();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isCreated());

        user.setUsername("Updated Username");
        user.setPassword("Updated Password");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    void testThatDeleteUserSuccessfullyReturnsNoContentDelete() throws Exception {
        User testUser = TestDataUtil.createTestUserA();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser))
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/users/" + testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser))
        ).andExpect(status().isNoContent());
    }


}
