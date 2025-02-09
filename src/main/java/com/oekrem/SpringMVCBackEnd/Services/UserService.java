package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.User;

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
