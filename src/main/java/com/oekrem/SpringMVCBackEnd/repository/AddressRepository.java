package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressRepository {
    Page<Address> findAll(Pageable pageable);
    Page<Address> getAddressesByUserId(Pageable pageable, Long id);

    Address addAddress(Address address);
    Address updateAddress(Address address);
    void deleteAddress(Long id);
    Optional<Address> getAddressById(Long id);
}
