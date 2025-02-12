package com.oekrem.SpringMVCBackEnd.exceptions.AddressExceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
