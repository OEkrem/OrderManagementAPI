package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressResponse> findAll();
    CreateAddressRequest addAddress(Long userId, CreateAddressRequest address);
    UpdateAddressRequest updateAddress(Long userId, UpdateAddressRequest address);
    void deleteAddress(Long id);
    AddressResponse getAddressById(Long id);

    List<AddressResponse> getAddressesByUserId(Long id);

    Optional<Address> validateAddress(Long id);


}
