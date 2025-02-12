package com.oekrem.SpringMVCBackEnd.exceptions.PaymentExceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException() {
        super();
    }
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
