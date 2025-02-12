package com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions;

public class PhoneNumberException extends RuntimeException {


    public PhoneNumberException() {
        super("Phone number is not valid");
    }

    public PhoneNumberException(String message) {
        super(message);
    }

}
