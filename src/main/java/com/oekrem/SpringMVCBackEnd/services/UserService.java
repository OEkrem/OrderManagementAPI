package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    Page<UserResponse> findAll(int page, int size);
    UserResponse addUser(CreateUserRequest createUserRequest);
    UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);
    UserResponse patchUser(Long id, PatchUserRequest patchUserRequest);
    void deleteUser(Long id);
    UserResponse getUserById(Long id);
    UserResponse getUserResponseByEmail(String email);
    User getUserByEmail(String email);

    User validateUser(Long id);
    Optional<User> validateUserEmail(String email);
}
