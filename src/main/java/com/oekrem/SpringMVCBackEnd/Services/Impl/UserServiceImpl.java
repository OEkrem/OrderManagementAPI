package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.Business.UserManager;
import com.oekrem.SpringMVCBackEnd.DataAccess.UserRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    private UserManager userManager;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserManager userManager, UserRepository userRepository) {
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
    public void addUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        if (user.getFirstName() == null) { throw new IllegalArgumentException("User is not found"); }

        List<Address> addressList = userRequest.getAddresses().stream()
                .map(addressRequest -> modelMapper.map(addressRequest, Address.class))
                .collect(Collectors.toList());
        addressList.forEach(address -> address.setUser(user));

        user.setAddresses(addressList);
        userRepository.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        if (user.getFirstName() == null) { throw new IllegalArgumentException("User is not found"); }

        List<Address> addressList = userRequest.getAddresses().stream()
                .map(addressRequest -> modelMapper.map(addressRequest, Address.class))
                .collect(Collectors.toList());
        addressList.forEach(address -> address.setUser(user));

        user.setAddresses(addressList);
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional
    public UserResponse getUserById(Long id) {
        User user =userRepository.getUserById(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserRequest getUserRequestById(Long id) {
        User user = userRepository.getUserById(id);
        return modelMapper.map(user, UserRequest.class);
    }
}
