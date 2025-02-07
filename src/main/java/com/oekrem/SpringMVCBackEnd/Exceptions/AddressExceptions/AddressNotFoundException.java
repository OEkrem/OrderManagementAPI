package com.oekrem.SpringMVCBackEnd.Exceptions.AddressExceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
