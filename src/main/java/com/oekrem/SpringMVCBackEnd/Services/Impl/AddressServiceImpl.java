package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.AddressRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import com.oekrem.SpringMVCBackEnd.Services.AddressService;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ModelMapper modelMapper;

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<AddressResponse> findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(address -> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressResponse getAddressById(Long id) {
        Address address = addressRepository.getAddressById(id);
        return modelMapper.map(address, AddressResponse.class);
    }

    @Override
    @Transactional
    public void addAddress(Long userId, CreateAddressRequest address) {
        User user = userService.validateUser(userId);
        user.getAddresses().add(modelMapper.map(address, Address.class));
        userService.updateUser(modelMapper.map(user, UserRequest.class));
    }

    @Override
    @Transactional
    public void updateAddress(Long userId, UpdateAddressRequest address) {
        User user = userService.validateUser(userId);
        for(Address address1 : user.getAddresses()) {
            if(address1.getId().equals(address.getId())) {
                address1 = modelMapper.map(address1, Address.class);
            }
        }
        userService.updateUser(modelMapper.map(user, UserRequest.class));
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {

        Optional<Address> existingAddress = Optional.ofNullable(addressRepository.getAddressById(id));
        if (existingAddress.isEmpty()) {
            throw new IllegalArgumentException("Address bulunamadı..");
        }

        if(id == null){return;}
        addressRepository.deleteAddress(id);
    }
/*
    @Override
    @Transactional
    public List<AddressResponse> getAddressByUserId(Long id) {
        UserResponse user = userService.getUserById(id);
        return user.getAddresses().stream().map(address -> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
    }
    */

}
