package com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.models.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    /*private final AddressMapper addressMapper;

    @Autowired
    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }*/

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

    public User toUserFromCreateUserRequest(CreateUserRequest userRequest) {

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
        user.getAddresses().forEach(address -> address.setUser(user));
        return user;
    }

    public CreateUserRequest toCreateUserRequest(User user) {
        /*,
                user.getAddresses()
                /*user.getAddresses().stream()
                        .map(addressMapper::toRequest)
                        .collect(Collectors.toList())*/
        return new CreateUserRequest(
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
    }

    public User toUserFromUpdateUserRequest(UpdateUserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        return user;
    }

    public UpdateUserRequest toUpdateUserRequest(User user) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setUsername(user.getUsername());
        updateUserRequest.setPassword(user.getPassword());
        updateUserRequest.setFirstName(user.getFirstName());
        updateUserRequest.setLastName(user.getLastName());
        updateUserRequest.setEmail(user.getEmail());
        updateUserRequest.setPhone(user.getPhone());
        return updateUserRequest;
    }

}
