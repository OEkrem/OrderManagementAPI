package com.oekrem.SpringMVCBackEnd.Exceptions.AddressExceptions;

public class AddressDoesntExitsException extends RuntimeException {
    public AddressDoesntExitsException() {
        super();
    }

    public AddressDoesntExitsException(String message) {
        super(message);
    }
}
