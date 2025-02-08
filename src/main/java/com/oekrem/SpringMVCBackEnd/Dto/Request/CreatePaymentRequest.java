package com.oekrem.SpringMVCBackEnd.Dto.Request;

import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.Models.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreatePaymentRequest {

    private String description;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;

    public CreatePaymentRequest() {
    }

    public CreatePaymentRequest(String description, BigDecimal amount, PaymentStatus paymentStatus, PaymentMethod paymentMethod, LocalDateTime date) {
        this.description = description;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "CreatePaymentRequest{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod +
                ", date=" + date +
                '}';
    }
}
