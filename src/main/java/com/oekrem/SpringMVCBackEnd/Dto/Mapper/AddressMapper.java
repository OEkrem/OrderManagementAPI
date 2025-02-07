package com.oekrem.SpringMVCBackEnd.Dto.Mapper;

import com.oekrem.SpringMVCBackEnd.Dto.Request.AddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private ModelMapper modelMapper;

    public AddressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Address toAddressFromRequest(AddressRequest addressRequest) {
        Address address = new Address();
        address.setId(null);
        address.setName(addressRequest.getName());
        address.setDoorNumber(addressRequest.getDoorNumber());
        address.setFloor(addressRequest.getFloor());
        address.setBuildingNumber(addressRequest.getBuildingNumber());
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setUser(null);
        return address;
    }

    public Address toAddressFromResponse(AddressResponse addressResponse) {
        User user = new User();
        user.setId(addressResponse.getUserId());
        Address address = new Address();
        address.setId(addressResponse.getId());
        address.setName(addressResponse.getName());
        address.setDoorNumber(addressResponse.getDoorNumber());
        address.setFloor(addressResponse.getFloor());
        address.setBuildingNumber(addressResponse.getBuildingNumber());
        address.setStreet(addressResponse.getStreet());
        address.setCity(addressResponse.getCity());
        address.setCountry(addressResponse.getCountry());
        address.setUser(user);
        return address;
    }

    public Address toAddressFromCreateAddressRequest(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setId(null);
        address.setName(createAddressRequest.getName());
        address.setDoorNumber(createAddressRequest.getDoorNumber());
        address.setFloor(createAddressRequest.getFloor());
        address.setBuildingNumber(createAddressRequest.getBuildingNumber());
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());
        address.setCountry(createAddressRequest.getCountry());
        address.setUser(null);
        return address;
    }

    public Address toAddressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest) {
        Address address = new Address();
        address.setId(updateAddressRequest.getId());
        address.setName(updateAddressRequest.getName());
        address.setDoorNumber(updateAddressRequest.getDoorNumber());
        address.setFloor(updateAddressRequest.getFloor());
        address.setBuildingNumber(updateAddressRequest.getBuildingNumber());
        address.setStreet(updateAddressRequest.getStreet());
        address.setCity(updateAddressRequest.getCity());
        address.setCountry(updateAddressRequest.getCountry());
        address.setUser(null);
        return address;
    }

    public AddressResponse toAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setName(address.getName());
        addressResponse.setDoorNumber(address.getDoorNumber());
        addressResponse.setFloor(address.getFloor());
        addressResponse.setBuildingNumber(address.getBuildingNumber());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setCity(address.getCity());
        addressResponse.setCountry(address.getCountry());
        return addressResponse;
    }
}
