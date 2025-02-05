package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.AddressRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Request.AddAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Services.AddressService;
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

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
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
    public void addAddress(AddAddressRequest address) {
        return;
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        return;
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
}
