package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.models.Address;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AddressService {
    Page<AddressResponse> findAll(int page, int size, Long userId);
    AddressResponse addAddress(Long userId, CreateAddressRequest address);
    AddressResponse updateAddress(Long userId, UpdateAddressRequest address);
    AddressResponse patchAddress(Long userId, PatchAddressRequest address);
    void deleteAddress(Long id);
    AddressResponse getAddressById(Long id);

    Optional<Address> validateAddress(Long id);


}
