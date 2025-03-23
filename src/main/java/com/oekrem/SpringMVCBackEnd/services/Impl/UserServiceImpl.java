package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
import com.oekrem.SpringMVCBackEnd.models.enums.Role;
import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.UserNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Page<UserResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toResponse);
    }

    @Override
    @Transactional
    public UserResponse addUser(CreateUserRequest createUserRequest) {
        if(userRepository.findUserByEmail(createUserRequest.email()).isPresent())
            throw new EMailTakenException("Email already exists");

        User user = userMapper.toUserFromCreateRequest(createUserRequest);
        user.setRoles(Set.of(Role.ROLE_USER));

        User savedUser = userRepository.addUser(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        validateUser(id);
        User user = userMapper.toUserFromUpdateRequest(updateUserRequest);
        user.setId(id);

        User updatedUser = userRepository.updateUser(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponse patchUser(Long id, PatchUserRequest patchUserRequest) {
        User user = validateUser(id);

        userMapper.patchUser(patchUserRequest, user);
        User savedUser = userRepository.updateUser(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        validateUser(id);
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional
    public UserResponse getUserById(Long id) {
        User user = validateUser(id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserResponseByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found") );
        return userMapper.toResponse(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found") );
    }


    @Override
    public User validateUser(Long id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Optional<User> validateUserEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}
