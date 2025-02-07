package com.oekrem.SpringMVCBackEnd.Exceptions.ProductExceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
