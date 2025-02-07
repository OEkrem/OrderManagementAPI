package com.oekrem.SpringMVCBackEnd.Dto.Mapper;

import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperIntegrationTest {

    @Autowired
    UserMapper userMapper;

    private User testUser;
    private UserResponse testUserResponse;
    private UserRequest testUserRequest;

    @Test
    public void testResponseToUser(){
        // test verisi
        testUserResponse = new UserResponse(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121");

        // mapping yapıldı
        testUser = userMapper.toUserFromResponse(testUserResponse);

        // karşılaştırmalar
        assertNotNull(testUser);
        assertEquals(testUserResponse.getId(), testUser.getId());
        assertEquals(testUserResponse.getEmail(), testUser.getEmail());
        System.out.println("User:         " + testUser);
    }

    @Test
    public void testRequestToUser(){
        testUserRequest = new UserRequest("ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121");
        testUserRequest.setId(1L);

        testUser = userMapper.toUserFromRequest(testUserRequest);

        assertNotNull(testUser);
        assertEquals(testUserRequest.getId(), testUser.getId());
        assertEquals(testUserRequest.getEmail(), testUser.getEmail());
        System.out.println("User:         " + testUser);
    }

    @Test
    public void testUserToResponse(){
        testUser = new User(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121", null);

        testUserResponse = userMapper.toResponse(testUser);

        assertNotNull(testUserResponse);
        assertEquals(testUserResponse.getId(), testUser.getId());
        assertEquals(testUserResponse.getEmail(), testUser.getEmail());
        System.out.println("UserResponse: " + testUserResponse);
    }

    @Test
    public void testUserToRequest(){
        testUser = new User(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121",null);

        testUserRequest = userMapper.toRequest(testUser);

        assertNotNull(testUserRequest);
        assertEquals(testUserRequest.getId(), testUser.getId());
        assertEquals(testUserRequest.getEmail(), testUser.getEmail());
        System.out.println("UserRequest: " + testUserRequest);
    }
}
