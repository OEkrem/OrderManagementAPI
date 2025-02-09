package com.oekrem.SpringMVCBackEnd.Exceptions.OrderExceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super();
    }
    public OrderNotFoundException(String message){
        super(message);
    }
}
