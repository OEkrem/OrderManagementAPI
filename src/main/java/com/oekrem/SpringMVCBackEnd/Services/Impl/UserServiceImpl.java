package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.BusinessService.UserBusinessService;
import com.oekrem.SpringMVCBackEnd.DataAccess.UserRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.UserDoesntExistsException;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    private UserBusinessService userManager;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserBusinessService userManager, UserRepository userRepository) {
        this.userManager = userManager;
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
        User user = modelMapper.map(userRequest, User.class);
        if (user.getFirstName() == null) { throw new IllegalArgumentException("User is not found"); }

        List<Address> addressList = userRequest.getAddresses().stream()
                .map(addressRequest -> modelMapper.map(addressRequest, Address.class))
                .collect(Collectors.toList());
        addressList.forEach(address -> address.setUser(user));

        user.setAddresses(addressList);
        return userRepository.addUser(user);
    }

    @Override
    @Transactional
    public User updateUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        if (user.getFirstName() == null) { throw new IllegalArgumentException("User is not found"); }

        List<Address> addressList = userRequest.getAddresses().stream()
                .map(addressRequest -> modelMapper.map(addressRequest, Address.class))
                .collect(Collectors.toList());
        addressList.forEach(address -> address.setUser(user));

        user.setAddresses(addressList);
        return userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.getUserById(id)
                .orElseThrow(() -> new UserDoesntExistsException("There is no user with id " + id)));
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional
    public UserResponse getUserById(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.getUserById(id)
                .orElseThrow(() -> new UserDoesntExistsException("User not found with id: " + id)));
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserRequest getUserRequestById(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.getUserById(id)
                .orElseThrow(() -> new UserDoesntExistsException("UserRequest not found wih id: " + id)));
        return modelMapper.map(user, UserRequest.class);
    }
}
