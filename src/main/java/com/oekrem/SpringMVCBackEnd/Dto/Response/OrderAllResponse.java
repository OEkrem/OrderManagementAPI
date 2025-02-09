package com.oekrem.SpringMVCBackEnd.Dto.Response;

import java.time.LocalDate;
import java.util.List;

public class OrderAllResponse {
    private Long id;
    private Long userId;
    private List<OrderDetailResponse> orderDetailIdList;
    private PaymentResponse payment;
    private LocalDate date;
    private Double total;

    public OrderAllResponse() {
    }

    public OrderAllResponse(Long id, Long userId, List<OrderDetailResponse> orderDetailIdList, PaymentResponse payment, LocalDate date, Double total) {
        this.id = id;
        this.userId = userId;
        this.orderDetailIdList = orderDetailIdList;
        this.payment = payment;
        this.date = date;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderDetailResponse> getOrderDetailIdList() {
        return orderDetailIdList;
    }

    public void setOrderDetailIdList(List<OrderDetailResponse> orderDetailIdList) {
        this.orderDetailIdList = orderDetailIdList;
    }

    public PaymentResponse getPayment() {
        return payment;
    }

    public void setPayment(PaymentResponse payment) {
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

    @Override
    public String toString() {
        return "OrderAllResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDetailIdList=" + orderDetailIdList +
                ", payment=" + payment +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
