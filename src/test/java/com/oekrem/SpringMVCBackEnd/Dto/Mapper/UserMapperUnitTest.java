package com.oekrem.SpringMVCBackEnd.Dto.Mapper;


import com.oekrem.SpringMVCBackEnd.Dto.Mapper.CustomMapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMapperUnitTest {

    @Mock
    @Qualifier("defaultModelMapper")
    private ModelMapper modelMapper;

    @InjectMocks
    private UserMapper userMapper;

    private User testUser;
    private UserResponse testUserResponse;


    @Test
    public void testResponseToUser(){
        // test verisi
        testUserResponse = new UserResponse(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121");
        User expectedUser = new User(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121", null);

        // Mock davranışı burada tanımlanıyor
        when(modelMapper.map(testUserResponse, User.class)).thenReturn(expectedUser);

        // mapping yapıldı
        testUser = userMapper.toUserFromResponse(testUserResponse);

        // karşılaştırmalar
        assertNotNull(testUser);
        assertEquals(testUserResponse.getId(), testUser.getId());
        assertEquals(testUserResponse.getEmail(), testUser.getEmail());
    }

    @Test
    public void testRequestToUser(){
        //testUserRequest = new UserRequest("ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121");
        //testUserRequest.setId(1L);
        User expectedUser = new User(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121", null);

        // Mock davranışı burada tanımlanıyor

    }

    @Test
    public void testUserToResponse(){
        testUser = new User(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121",null);
        UserResponse expectedUser = new UserResponse(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121");
        when(modelMapper.map(testUser, UserResponse.class)).thenReturn(expectedUser);

        testUserResponse = userMapper.toResponse(testUser);

        assertNotNull(testUserResponse);
        assertEquals(testUserResponse.getId(), testUser.getId());
        assertEquals(testUserResponse.getEmail(), testUser.getEmail());
    }

    @Test
    public void testUserToRequest(){
        testUser = new User(1L, "ekrem37", "123", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "3213212121",null);

    }

}
