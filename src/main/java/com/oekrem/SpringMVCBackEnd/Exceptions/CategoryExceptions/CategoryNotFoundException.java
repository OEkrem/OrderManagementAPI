package com.oekrem.SpringMVCBackEnd.Exceptions.CategoryExceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super();
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
