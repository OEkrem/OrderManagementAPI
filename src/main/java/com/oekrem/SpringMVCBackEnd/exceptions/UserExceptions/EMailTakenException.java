package com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions;

public class EMailTakenException extends RuntimeException{

    public EMailTakenException(){
        super("Email is already taken!");
    }

    public EMailTakenException(String message){
        super(message);
    }
}
