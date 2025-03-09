package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.models.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    @Mapping(target = "userId", source = "user.id")
    AddressResponse toResponse(Address address);
    Address toAddressFromCreateRequest(CreateAddressRequest createAddressRequest);
    Address toAddressFromUpdateRequest(UpdateAddressRequest updateAddressRequest);

    @Mapping(target = "id", ignore = true)
    void patchAdress(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);

}
