package com.oekrem.SpringMVCBackEnd.Exceptions.PaymentExceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException() {
        super();
    }
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
