package com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions;

public class TokenAlreadyExpiredException extends RuntimeException {
    public TokenAlreadyExpiredException() {
        super();
    }
    public TokenAlreadyExpiredException(String message) {
        super(message);
    }
}
