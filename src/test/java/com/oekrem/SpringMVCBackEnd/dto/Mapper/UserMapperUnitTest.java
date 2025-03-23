package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.Address;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.models.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

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
                .roles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN))
                .addresses(List.of(Address.builder().id(1L).build()))
                .refreshToken(null)
                .build();

        UserResponse userResponse = userMapper.toResponse(user);

        assertEquals(userResponse.id(), user.getId());
        assertEquals(userResponse.firstName(), user.getFirstName());
        assertEquals(userResponse.lastName(), user.getLastName());
        assertEquals(userResponse.username(), user.getUsername());
        assertEquals(userResponse.email(), user.getEmail());
        assertEquals(userResponse.password(), user.getPassword());
        assertEquals(userResponse.phone(), user.getPhone());
        assertEquals(userResponse.roles(), user.getRoles());
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
                .roles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN))
                .build();
        User user = userMapper.toUserFromCreateRequest(createUserRequest);

        assertEquals(user.getFirstName(), createUserRequest.firstName());
        assertEquals(user.getLastName(), createUserRequest.lastName());
        assertEquals(user.getUsername(), createUserRequest.username());
        assertEquals(user.getEmail(), createUserRequest.email());
        assertEquals(user.getPassword(), createUserRequest.password());
        assertEquals(user.getPhone(), createUserRequest.phone());
        assertEquals(user.getRoles(), createUserRequest.roles());
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
