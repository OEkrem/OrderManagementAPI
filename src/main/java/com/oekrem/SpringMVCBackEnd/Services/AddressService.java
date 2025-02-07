package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> findAll();
    void addAddress(Long userId, CreateAddressRequest address);
    void updateAddress(Long userId, UpdateAddressRequest address);
    void deleteAddress(Long id);
    AddressResponse getAddressById(Long id);

    //List<AddressResponse> getAddressByUserId(Long id);
}
