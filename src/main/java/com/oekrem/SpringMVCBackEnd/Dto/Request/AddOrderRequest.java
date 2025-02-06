package com.oekrem.SpringMVCBackEnd.Dto.Request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddOrderRequest {

    private Long userId;
    private List<AddOrderDetailRequest> orderDetailList;
    private PaymentRequest payment;
    private LocalDate date;
    private Double total;

    public AddOrderRequest() {
    }

    public AddOrderRequest(Long userId, List<AddOrderDetailRequest> orderDetailList, PaymentRequest payment, LocalDate date, Double total) {
        this.userId = userId;
        this.orderDetailList = orderDetailList;
        this.payment = payment;
        this.date = date;
        this.total = total;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AddOrderDetailRequest> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<AddOrderDetailRequest> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public PaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(PaymentRequest payment) {
        this.payment = payment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
