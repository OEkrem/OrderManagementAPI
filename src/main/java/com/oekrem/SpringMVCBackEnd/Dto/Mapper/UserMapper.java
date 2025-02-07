package com.oekrem.SpringMVCBackEnd.Dto.Mapper;

import com.oekrem.SpringMVCBackEnd.Dto.Request.UserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;
    private ModelMapper modelMapper;

    @Autowired
    public UserMapper(@Qualifier("defaultModelMapper") ModelMapper modelMapper, AddressMapper addressMapper) {
        this.modelMapper = modelMapper;
        this.addressMapper = addressMapper;
    }

    public User toUserFromRequest(UserRequest userRequest) {

        User user = new User();
        user.setId(null);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        //user.setAddresses(userRequest.getAddresses());
        /*user.setAddresses(
                userRequest.getAddresses().stream()
                        .map(addressMapper::toAddressFromRequest)
                        .collect(Collectors.toList())
        );*/
        user.getAddresses().stream().forEach(address -> address.setUser(user));
        return user;
    }

    public User toUserFromResponse(UserResponse userResponse) {
        User user = new User();
        user.setId(userResponse.getId());
        user.setUsername(userResponse.getUsername());
        user.setPassword(userResponse.getPassword());
        user.setFirstName(userResponse.getFirstName());
        user.setLastName(userResponse.getLastName());
        user.setEmail(userResponse.getEmail());
        user.setPhone(userResponse.getPhone());
        /*user.setAddresses(
                userResponse.getAddresses().stream()
                        .map(addressMapper::toAddressFromResponse)
                        .collect(Collectors.toList())
        );*/
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()/*,
                user.getAddresses().stream()
                        .map(addressMapper::toResponse)
                        .collect(Collectors.toList())*/
        );
        return userResponse;
    }

    public UserRequest toRequest(User user) {
        UserRequest userRequest = new UserRequest(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()/*,
                user.getAddresses()
                /*user.getAddresses().stream()
                        .map(addressMapper::toRequest)
                        .collect(Collectors.toList())*/
        );
        return userRequest;
    }

}
