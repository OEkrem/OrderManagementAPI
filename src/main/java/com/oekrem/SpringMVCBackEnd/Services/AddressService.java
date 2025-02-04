package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Models.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);
    Address getAddressById(int id);
}
