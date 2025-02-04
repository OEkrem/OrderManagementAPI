package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.DataAccess.AddressRepository;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final UserService userService;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository; this.userService = userService;}

    @Override
    @Transactional
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public void addAddress(Address address) {
        Optional<User> existingUser = Optional.ofNullable(userService.getUserById(address.getUser().getId().intValue()));
        if(existingUser.isEmpty())
            throw new IllegalArgumentException("User bulunamadı!");

        address.setUser(existingUser.get());
        addressRepository.addAddress(address);
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {

        Optional<Address> existingAddress = Optional.ofNullable(addressRepository.getAddressById(address.getId().intValue()));
        if (existingAddress.isEmpty()) {
            throw new IllegalArgumentException("Address bulunamadı.");
        }

        if(address.getId() == null){return;}
        addressRepository.updateAddress(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Address address) {

        Optional<Address> existingAddress = Optional.ofNullable(addressRepository.getAddressById(address.getId().intValue()));
        if (existingAddress.isEmpty()) {
            throw new IllegalArgumentException("Address bulunamadı..");
        }

        if(address.getId() == null){return;}
        addressRepository.deleteAddress(address);
    }

    @Override
    @Transactional
    public Address getAddressById(int id) {
        return addressRepository.getAddressById(id);
    }
}
