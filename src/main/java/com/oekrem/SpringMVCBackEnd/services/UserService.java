package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.User;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();
    CreateUserRequest addUser(CreateUserRequest createUserRequest);
    UpdateUserRequest updateUser(Long id, UpdateUserRequest updateUserRequest);
    void deleteUser(Long id);
    UserResponse getUserById(Long id);

    User validateUser(Long id);
    void validateUserEmail(String email);
}
