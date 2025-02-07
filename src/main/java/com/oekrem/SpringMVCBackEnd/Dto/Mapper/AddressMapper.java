package com.oekrem.SpringMVCBackEnd.Dto.Mapper;

import com.oekrem.SpringMVCBackEnd.Dto.Request.AddressRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private ModelMapper modelMapper;

    public AddressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Address toAddressFromRequest(AddressRequest addressRequest) {
        Address address = new Address(
                null,
                addressRequest.getName(),
                addressRequest.getDoorNumber(),
                addressRequest.getFloor(),
                addressRequest.getBuildingNumber(),
                addressRequest.getStreet(),
                addressRequest.getCity(),
                addressRequest.getCountry(),
                null
        );
        return address;
    }

    public Address toAddressFromResponse(AddressResponse addressResponse) {
        Address address = new Address(
                addressResponse.getId(),
                addressResponse.getName(),
                addressResponse.getDoorNumber(),
                addressResponse.getFloor(),
                addressResponse.getBuildingNumber(),
                addressResponse.getStreet(),
                addressResponse.getCity(),
                addressResponse.getCountry(),
                null
        );
        return address;
    }

    public AddressResponse toResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse(
                address.getId(),
                address.getName(),
                address.getFloor(),
                address.getDoorNumber(),
                address.getBuildingNumber(),
                address.getStreet(),
                address.getCity(),
                address.getCountry()
        );
        return addressResponse;
    }

    public AddressRequest toRequest(Address address) {
        AddressRequest addressRequest = new AddressRequest(
                address.getName(),
                address.getFloor(),
                address.getDoorNumber(),
                address.getBuildingNumber(),
                address.getStreet(),
                address.getCity(),
                address.getCountry()
        );
        return addressRequest;
    }
}
