package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Page<AddressResponse> findAll(int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addressList;
        if (userId != null) {
            userService.validateUser(userId);
            addressList = addressRepository.getAddressesByUserId(pageable, userId);
        }
        else
            addressList = addressRepository.findAll(pageable);

        return addressList.map(addressMapper::toResponse);
    }

    @Override
    @Transactional
    public AddressResponse getAddressById(Long id) {
        Address address = addressRepository.getAddressById(id)
                .orElseThrow(AddressNotFoundException::new);

        return addressMapper.toResponse(address);
    }

    @Override
    @Transactional
    public AddressResponse addAddress(Long userId, CreateAddressRequest address) {
        userService.validateUser(userId);
        Address addressToAdd = addressMapper.toAddressFromCreateRequest(address);
        addressToAdd.setUser(User.builder().id(userId).build());
        Address savedAddress = addressRepository.addAddress(addressToAdd);
        return addressMapper.toResponse(savedAddress);
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(Long userId, UpdateAddressRequest address) {
        User user = userService.validateUser(userId);
        validateAddress(address.getId())
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        Address addressToUpdate = addressMapper.toAddressFromUpdateRequest(address);
        addressToUpdate.setUser(user);
        Address updatedAddress = addressRepository.updateAddress(addressToUpdate);
        return addressMapper.toResponse(updatedAddress);
    }

    @Override
    @Transactional
    public AddressResponse patchAddress(Long userId, PatchAddressRequest address) {
        User user = userService.validateUser(userId);
        Address ad = validateAddress(address.id())
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if(ad.getUser() != null && !Objects.equals(ad.getUser().getId(), user.getId()))
            throw new AddressNotFoundException("User and Address not matching each other. Please use valid user.");

        addressMapper.patchAddress(address, ad);
        addressRepository.updateAddress(ad);
        return addressMapper.toResponse(ad);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.getAddressById(id)
                .orElseThrow(() -> new AddressNotFoundException("There is no address with this id:" + id));

        addressRepository.deleteAddress(id);
    }

    @Override
    public Optional<Address> validateAddress(Long id) {
        return addressRepository.getAddressById(id);
    }
}
