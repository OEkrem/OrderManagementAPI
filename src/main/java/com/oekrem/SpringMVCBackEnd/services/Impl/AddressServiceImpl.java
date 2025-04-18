package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.repository.AddressRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.AddressMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.AddressExceptions.AddressNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Address;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.AddressService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    @Transactional
    @Cacheable(value = "addresses", key = "'page:' + #page + '-size:' + #size + '-userId:' + #userId")
    public PageResponse<AddressResponse> findAll(int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addressPage;
        if (userId != null) {
            userService.validateUser(userId);
            addressPage = addressRepository.getAddressesByUserId(pageable, userId);
        }
        else
            addressPage = addressRepository.findAll(pageable);
        Page<AddressResponse> responsesPage = addressPage.map(addressMapper::toResponse);
        return PageResponse.fromPage(responsesPage);
    }

    @Override
    @Transactional
    public AddressResponse getAddressById(Long addressId) {
        Address address = addressRepository.getAddressById(addressId)
                .orElseThrow(AddressNotFoundException::new);

        return addressMapper.toResponse(address);
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public AddressResponse addAddress(CreateAddressRequest address) {
        userService.validateUser(address.userId());
        Address addressToAdd = addressMapper.toAddressFromCreateRequest(address);
        addressToAdd.setUser(User.builder().id(address.userId()).build());
        Address savedAddress = addressRepository.addAddress(addressToAdd);
        return addressMapper.toResponse(savedAddress);
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public AddressResponse updateAddress(Long addressId, UpdateAddressRequest address) {
        validateAddress(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        Address addressToUpdate = addressMapper.toAddressFromUpdateRequest(address);
        Address updatedAddress = addressRepository.updateAddress(addressToUpdate);
        return addressMapper.toResponse(updatedAddress);
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public AddressResponse patchAddress(Long addressId, PatchAddressRequest address) {
        Address ad = validateAddress(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        /*if(ad.getUser() != null && !Objects.equals(ad.getUser().getId(), ad.getUser().getId()))
            throw new AddressNotFoundException("User and Address not matching each other. Please use valid user.");*/

        addressMapper.patchAddress(address, ad);
        Address patchedAddress = addressRepository.updateAddress(ad);
        return addressMapper.toResponse(patchedAddress);
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public void deleteAddress(Long addressId) {
        addressRepository.getAddressById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("There is no address with this id:" + addressId));

        addressRepository.deleteAddress(addressId);
    }

    @Override
    public Optional<Address> validateAddress(Long addressId) {
        return addressRepository.getAddressById(addressId);
    }
}
