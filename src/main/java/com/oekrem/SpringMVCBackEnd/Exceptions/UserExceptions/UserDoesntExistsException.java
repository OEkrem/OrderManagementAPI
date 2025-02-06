package com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions;

public class UserDoesntExistsException extends RuntimeException {

    public UserDoesntExistsException() {
        super("User doesn't exists!");
    }

    public UserDoesntExistsException(String message) {
        super(message);
    }
}
