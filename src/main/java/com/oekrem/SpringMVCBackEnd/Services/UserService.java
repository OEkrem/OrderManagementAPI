package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();
    void addUser(UserRequest userRequest);
    void updateUser(UserRequest userRequest);
    void deleteUser(Long id);
    UserResponse getUserById(Long id);
}
