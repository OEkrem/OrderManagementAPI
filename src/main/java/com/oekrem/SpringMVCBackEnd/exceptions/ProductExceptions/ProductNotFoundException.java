package com.oekrem.SpringMVCBackEnd.exceptions.ProductExceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
