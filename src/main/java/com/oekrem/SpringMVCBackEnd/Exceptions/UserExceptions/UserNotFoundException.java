package com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User doesn't exists!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
