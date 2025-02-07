package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> findAll();
    Address addAddress(Address address);
    Address updateAddress(Address address);
    void deleteAddress(Long id);
    Optional<Address> getAddressById(Long id);

    List<Address> getAddressesByUserId(Long id);
}
