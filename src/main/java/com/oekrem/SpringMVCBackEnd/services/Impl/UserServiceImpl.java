package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.UserNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse addUser(CreateUserRequest createUserRequest) {
        if(userRepository.findUserByEmail(createUserRequest.getEmail()).isPresent())
            throw new EMailTakenException("Email already exists");

        User user = userMapper.toUserFromCreateRequest(createUserRequest);

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
