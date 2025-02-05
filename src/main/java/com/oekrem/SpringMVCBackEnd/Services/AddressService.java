package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.AddAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;

import java.util.List;

public interface AddressService {
    List<AddressResponse> findAll();
    void addAddress(AddAddressRequest address);
    void updateAddress(Address address);
    void deleteAddress(Long id);
    AddressResponse getAddressById(Long id);
}
