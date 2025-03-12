package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.models.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressMapperUnitTest {

    private AddressMapper addressMapper;

    @BeforeEach
    public void setUp() {
        addressMapper = Mappers.getMapper(AddressMapper.class);
    }

    @Test
    public void shouldMapAddressToAddressResponse() {
        Address address = Address.builder()
                .id(1L)
                .name("Address 1")
                .doorNumber(4)
                .buildingNumber("4")
                .city("City 1")
                .street("Street 1")
                .country("Country 1")
                .floor(4)
                .build();

        AddressResponse response = addressMapper.toResponse(address);

        assertEquals(address.getId(), response.getId());
        assertEquals(address.getName(), response.getName());
        assertEquals(address.getDoorNumber(), response.getDoorNumber());
        assertEquals(address.getBuildingNumber(), response.getBuildingNumber());
        assertEquals(address.getCity(), response.getCity());
        assertEquals(address.getStreet(), response.getStreet());
        assertEquals(address.getCountry(), response.getCountry());
        assertEquals(address.getFloor(), response.getFloor());
    }

    @Test
    public void shouldMapCreateRequestToAddress(){
        CreateAddressRequest createAddressRequest = CreateAddressRequest.builder()
                .name("Address 1")
                .doorNumber(4)
                .buildingNumber("4")
                .city("City 1")
                .street("Street 1")
                .country("Country 1")
                .floor(4)
                .build();

        Address address = addressMapper.toAddressFromCreateRequest(createAddressRequest);

        assertEquals(createAddressRequest.name(), address.getName());
        assertEquals(createAddressRequest.doorNumber(), address.getDoorNumber());
        assertEquals(createAddressRequest.buildingNumber(), address.getBuildingNumber());
        assertEquals(createAddressRequest.city(), address.getCity());
        assertEquals(createAddressRequest.street(), address.getStreet());
        assertEquals(createAddressRequest.country(), address.getCountry());
        assertEquals(createAddressRequest.floor(), address.getFloor());
    }

    @Test
    public void shouldMapUpdateRequestToAddress(){
        UpdateAddressRequest updateAddressRequest = UpdateAddressRequest.builder()
                .name("Address 1")
                .doorNumber(4)
                .buildingNumber("4")
                .city("City 1")
                .street("Street 1")
                .country("Country 1")
                .floor(4)
                .build();

        Address address = addressMapper.toAddressFromUpdateRequest(updateAddressRequest);

        assertEquals(updateAddressRequest.name(), address.getName());
        assertEquals(updateAddressRequest.doorNumber(), address.getDoorNumber());
        assertEquals(updateAddressRequest.buildingNumber(), address.getBuildingNumber());
        assertEquals(updateAddressRequest.city(), address.getCity());
        assertEquals(updateAddressRequest.street(), address.getStreet());
        assertEquals(updateAddressRequest.country(), address.getCountry());
        assertEquals(updateAddressRequest.floor(), address.getFloor());
    }

    @Test
    public void shouldMapPatchAddressRequestToAddress(){
        Address address = Address.builder().build();
        PatchAddressRequest patchAddressRequest = PatchAddressRequest.builder()
                .name("Address 1")
                .doorNumber(4)
                .buildingNumber("4")
                .city("City 1")
                .street("Street 1")
                .country("Country 1")
                .floor(4)
                .build();

        addressMapper.patchAddress(patchAddressRequest, address);

        assertEquals(patchAddressRequest.name(), address.getName());
        assertEquals(patchAddressRequest.doorNumber(), address.getDoorNumber());
        assertEquals(patchAddressRequest.buildingNumber(), address.getBuildingNumber());
        assertEquals(patchAddressRequest.city(), address.getCity());
        assertEquals(patchAddressRequest.street(), address.getStreet());
        assertEquals(patchAddressRequest.country(), address.getCountry());
        assertEquals(patchAddressRequest.floor(), address.getFloor());
    }

}
