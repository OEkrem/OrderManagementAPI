package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.repository.AddressRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper.AddressMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.AddressExceptions.AddressNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Address;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.AddressService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper modelMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    @Transactional
    public List<AddressResponse> findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(address -> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressResponse getAddressById(Long id) {
        Address address = addressRepository.getAddressById(id)
                .orElseThrow(AddressNotFoundException::new);

        return modelMapper.map(address, AddressResponse.class);
    }

    @Override
    @Transactional
    public CreateAddressRequest addAddress(Long userId, CreateAddressRequest address) {
        userService.validateUser(userId);
        Address addressToAdd = addressMapper.toAddressFromCreateAddressRequest(address);
        User user = new User(); user.setId(userId);
        addressToAdd.setUser(user);
        addressRepository.addAddress(addressToAdd);
        return address;
    }

    @Override
    @Transactional
    public UpdateAddressRequest updateAddress(Long userId, UpdateAddressRequest address) {
        userService.validateUser(userId);
        validateAddress(address.getId())
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        Address addressToUpdate = addressMapper.toAddressFromUpdateAddressRequest(address);
        User user = new User(); user.setId(userId);
        addressToUpdate.setUser(user);
        addressRepository.updateAddress(addressToUpdate);
        return address;
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.getAddressById(id)
                .orElseThrow(() -> new AddressNotFoundException("There is no address with this id:" + id));

        addressRepository.deleteAddress(id);
    }

    @Override
    @Transactional
    public List<AddressResponse> getAddressesByUserId(Long id) {
        userService.validateUser(id);
        List<Address> addressList = addressRepository.getAddressesByUserId(id);
        System.out.println(addressList.stream().map(address -> addressMapper.toAddressResponse(address)).collect(Collectors.toList()));
        return addressList.stream().map(u -> addressMapper.toAddressResponse(u)).collect(Collectors.toList());
    }


    @Override
    public Optional<Address> validateAddress(Long id) {
        return addressRepository.getAddressById(id);
    }

}
