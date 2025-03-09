package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.Address;
import com.oekrem.SpringMVCBackEnd.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperUnitTest {

    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    public void shouldMapUserToResponse() {
        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@gmail.com")
                .password("password")
                .phone("123456789")
                .addresses(List.of(Address.builder().id(1L).build()))
                .refreshToken(null)
                .build();

        UserResponse userResponse = userMapper.toResponse(user);

        assertEquals(userResponse.getId(), user.getId());
        assertEquals(userResponse.getFirstName(), user.getFirstName());
        assertEquals(userResponse.getLastName(), user.getLastName());
        assertEquals(userResponse.getUsername(), user.getUsername());
        assertEquals(userResponse.getEmail(), user.getEmail());
        assertEquals(userResponse.getPassword(), user.getPassword());
        assertEquals(userResponse.getPhone(), user.getPhone());
    }

    @Test
    public void shouldMapCreateRequestToUser() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@gmail.com")
                .password("password")
                .phone("123456789")
                .build();
        User user = userMapper.toUserFromCreateRequest(createUserRequest);

        assertEquals(user.getFirstName(), createUserRequest.getFirstName());
        assertEquals(user.getLastName(), createUserRequest.getLastName());
        assertEquals(user.getUsername(), createUserRequest.getUsername());
        assertEquals(user.getEmail(), createUserRequest.getEmail());
        assertEquals(user.getPassword(), createUserRequest.getPassword());
        assertEquals(user.getPhone(), createUserRequest.getPhone());
    }

    @Test
    public void shouldMapUpdateRequestToUser() {
        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@gmail.com")
                .password("password")
                .phone("123456789")
                .build();
        User user = userMapper.toUserFromUpdateRequest(updateUserRequest);
        assertEquals(user.getFirstName(), updateUserRequest.getFirstName());
        assertEquals(user.getLastName(), updateUserRequest.getLastName());
        assertEquals(user.getUsername(), updateUserRequest.getUsername());
        assertEquals(user.getEmail(), updateUserRequest.getEmail());
        assertEquals(user.getPassword(), updateUserRequest.getPassword());
        assertEquals(user.getPhone(), updateUserRequest.getPhone());
    }

}
