package com.oekrem.SpringMVCBackEnd.Exceptions.UserExceptions;

public class UsernameTakenException extends RuntimeException {

    public UsernameTakenException() {
        super("Username is already taken!");
    }

    public UsernameTakenException(String message) {
        super(message);
    }
}
