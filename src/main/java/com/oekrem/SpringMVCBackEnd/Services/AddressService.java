package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressResponse> findAll();
    CreateAddressRequest addAddress(Long userId, CreateAddressRequest address);
    UpdateAddressRequest updateAddress(Long userId, UpdateAddressRequest address);
    void deleteAddress(Long id);
    AddressResponse getAddressById(Long id);

    List<AddressResponse> getAddressByUserId(Long id);

    Optional<Address> validateAddress(Long id);


}
