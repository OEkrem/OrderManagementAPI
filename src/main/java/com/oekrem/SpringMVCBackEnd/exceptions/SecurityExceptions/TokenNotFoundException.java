package com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions;

public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException(){
        super();
    }
    public TokenNotFoundException(String message){
        super(message);
    }
}
