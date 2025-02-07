package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.UserRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Mapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.UserNotFoundException;
import com.oekrem.SpringMVCBackEnd.Models.User;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserMapper userMapper;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User addUser(UserRequest userRequest) {
        if(validateUserEmail(userRequest.getEmail()).isPresent())
            throw new EMailTakenException("This E-Mail already exists!");
        User user = userMapper.toUserFromRequest(userRequest);
        return userRepository.addUser(user);
    }

    @Override
    @Transactional
    public User updateUser(UserRequest userRequest) {
        validateUser(userRequest.getId());
        User user = modelMapper.map(userRequest, User.class);
        return userRepository.updateUser(user);
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
    public Optional<User> validateUserEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
