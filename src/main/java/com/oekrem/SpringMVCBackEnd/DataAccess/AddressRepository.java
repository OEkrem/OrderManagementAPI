package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface AddressRepository {
    List<Address> findAll();
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);
    Address getAddressById(int id);
}
