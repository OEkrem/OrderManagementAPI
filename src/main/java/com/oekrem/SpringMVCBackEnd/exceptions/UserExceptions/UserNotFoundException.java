package com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User doesn't exists!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
