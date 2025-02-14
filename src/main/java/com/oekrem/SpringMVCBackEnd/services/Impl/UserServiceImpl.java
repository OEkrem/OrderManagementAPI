package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.UserNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CreateUserRequest addUser(CreateUserRequest createUserRequest) {
        validateUserEmail(createUserRequest.getEmail());
        User user = userMapper.toUserFromCreateUserRequest(createUserRequest);
        userRepository.addUser(user);
        return createUserRequest;
    }

    @Override
    @Transactional
    public UpdateUserRequest updateUser(Long id, UpdateUserRequest updateUserRequest) {
        validateUser(id);
        User user = userMapper.toUserFromUpdateUserRequest(updateUserRequest);
        user.setId(id);
        return updateUserRequest;
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
        return modelMapper.map(user, UserResponse.class);
    }


    @Override
    public User validateUser(Long id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public void validateUserEmail(String email) {
        if(userRepository.getUserByEmail(email).isPresent())
            throw new EMailTakenException("User already exist with email: " + email);
    }
}
