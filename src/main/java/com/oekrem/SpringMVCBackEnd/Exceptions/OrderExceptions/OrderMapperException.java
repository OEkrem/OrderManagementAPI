package com.oekrem.SpringMVCBackEnd.Exceptions.OrderExceptions;

public class OrderMapperException extends RuntimeException{
    public OrderMapperException(String message) {
        super(message);
    }
    public OrderMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
