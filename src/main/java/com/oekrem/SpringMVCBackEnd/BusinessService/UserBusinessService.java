package com.oekrem.SpringMVCBackEnd.BusinessService;

import com.oekrem.SpringMVCBackEnd.Services.UserService;

public interface UserBusinessService {

    void validateEmail(String email);
    void validateUserName(String userName);
    void validatePhoneNumber(String phoneNumber);
    void validateUser(String email, String password);

}
