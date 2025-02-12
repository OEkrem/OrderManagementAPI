package com.oekrem.SpringMVCBackEnd.exceptions.CategoryExceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super();
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
