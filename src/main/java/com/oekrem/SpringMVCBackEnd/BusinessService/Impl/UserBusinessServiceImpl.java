package com.oekrem.SpringMVCBackEnd.BusinessService.Impl;

import com.oekrem.SpringMVCBackEnd.BusinessService.UserBusinessService;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.PhoneNumberException;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.UserDoesntExistsException;
import com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions.UsernameTakenException;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessServiceImpl implements UserBusinessService {

    private UserService userService;

    @Autowired
    public UserBusinessServiceImpl(UserService userService) {this.userService = userService;}


    @Override
    public void validateEmail(String email) {
        if(userService.isEmailAlreadyTaken(email))
            throw new EMailTakenException("Email is already taken");
    }

    @Override
    public void validateUserName(String userName) {
        if(userService.isUserNameAlreadyTaken(userName))
            throw new UsernameTakenException("Username is already taken");
    }

    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if(userService.isPhoneNumberFormCorrect(phoneNumber))
            throw new PhoneNumberException("Phone number is unsafe");
    }

    @Override
    public void validateUser(String email, String password) {
        if(userService.isUserExists(email, password))
            throw new UserDoesntExistsException("User is not found");
    }


}
