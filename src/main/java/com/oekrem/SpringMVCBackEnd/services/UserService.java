package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAll();
    UserResponse addUser(CreateUserRequest createUserRequest);
    UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);
    void deleteUser(Long id);
    UserResponse getUserById(Long id);
    User getUserByEmail(String email);

    User validateUser(Long id);
    Optional<User> validateUserEmail(String email);
}
