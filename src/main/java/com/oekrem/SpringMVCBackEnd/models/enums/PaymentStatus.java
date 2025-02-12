package com.oekrem.SpringMVCBackEnd.models.enums;

public enum PaymentStatus {
    SUCCESSFUL("Succesful"),
    FAILED("Failed"),
    PENDING("Pending");

    private String status;
    PaymentStatus(String status) {this.status = status;}
    public String getStatus() {return status;}

}
