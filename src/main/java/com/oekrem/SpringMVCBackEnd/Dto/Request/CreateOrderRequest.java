package com.oekrem.SpringMVCBackEnd.Dto.Request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateOrderRequest {

    private Long userId;
    private List<CreateOrderDetailRequest> orderDetailList;
    private CreatePaymentRequest payment;
    private LocalDate date;
    private Double total;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long userId, List<CreateOrderDetailRequest> orderDetailList, CreatePaymentRequest payment, LocalDate date, Double total) {
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

    public List<CreateOrderDetailRequest> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<CreateOrderDetailRequest> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public CreatePaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(CreatePaymentRequest payment) {
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
