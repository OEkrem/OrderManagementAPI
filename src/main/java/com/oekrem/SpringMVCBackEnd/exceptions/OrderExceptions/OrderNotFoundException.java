package com.oekrem.SpringMVCBackEnd.exceptions.OrderExceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super();
    }
    public OrderNotFoundException(String message){
        super(message);
    }
}
