package com.oekrem.SpringMVCBackEnd.Exceptions.OrderDetailExceptions;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(){
        super();
    }
    public OrderDetailNotFoundException(String message){
        super(message);
    }
}
