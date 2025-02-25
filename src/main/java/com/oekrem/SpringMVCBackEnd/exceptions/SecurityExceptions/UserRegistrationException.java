package com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException() {
        super();
    }
    public UserRegistrationException(String message) {
        super(message);
    }
}
