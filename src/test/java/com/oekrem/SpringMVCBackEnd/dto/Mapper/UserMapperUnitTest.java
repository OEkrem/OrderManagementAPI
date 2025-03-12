package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
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

        assertEquals(user.getFirstName(), createUserRequest.firstName());
        assertEquals(user.getLastName(), createUserRequest.lastName());
        assertEquals(user.getUsername(), createUserRequest.username());
        assertEquals(user.getEmail(), createUserRequest.email());
        assertEquals(user.getPassword(), createUserRequest.password());
        assertEquals(user.getPhone(), createUserRequest.phone());
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
        assertEquals(user.getFirstName(), updateUserRequest.firstName());
        assertEquals(user.getLastName(), updateUserRequest.lastName());
        assertEquals(user.getUsername(), updateUserRequest.username());
        assertEquals(user.getEmail(), updateUserRequest.email());
        assertEquals(user.getPassword(), updateUserRequest.password());
        assertEquals(user.getPhone(), updateUserRequest.phone());
    }

    @Test
    public void shouldMapPatchRequestToUser() {
        PatchUserRequest patchUserRequest = PatchUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@gmail.com")
                .password("password")
                .phone("123456789")
                .build();
        User user = User.builder().build();
        userMapper.patchUser(patchUserRequest, user);

        assertEquals(patchUserRequest.firstName(), user.getFirstName());
        assertEquals(patchUserRequest.lastName(), user.getLastName());
        assertEquals(patchUserRequest.username(), user.getUsername());
        assertEquals(patchUserRequest.email(), user.getEmail());
        assertEquals(patchUserRequest.password(), user.getPassword());
        assertEquals(patchUserRequest.phone(), user.getPhone());
    }

}
