package com.oekrem.SpringMVCBackEnd.Dto.Request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateOrderRequest {

    /*private List<CreateOrderDetailRequest> orderDetailList;
    private CreatePaymentRequest payment;*/
    private LocalDate date;
    private Double total;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(/*List<CreateOrderDetailRequest> orderDetailList, CreatePaymentRequest payment, */LocalDate date, Double total) {
        /*this.orderDetailList = orderDetailList;
        this.payment = payment;*/
        this.date = date;
        this.total = total;
    }
/*
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
    }*/

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

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                //"orderDetailList=" + orderDetailList +
                //", payment=" + payment +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
