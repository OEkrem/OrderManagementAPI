package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(source = "roles", target = "roles")
    UserResponse toResponse(User user);

    User toUserFromCreateRequest(CreateUserRequest createUserRequest);
    @Mapping(source = "roles", target = "roles")
    User toUserFromUpdateRequest(UpdateUserRequest updateUserRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roles", target = "roles")
    void patchUser(PatchUserRequest patchUserRequest, @MappingTarget User user);

}
