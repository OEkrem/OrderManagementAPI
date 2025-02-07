package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAll();
    User addUser(UserRequest userRequest);
    User updateUser(UserRequest userRequest);
    void deleteUser(Long id);
    UserResponse getUserById(Long id);

    User validateUser(Long id);
    Optional<User> validateUserEmail(String email);
}
