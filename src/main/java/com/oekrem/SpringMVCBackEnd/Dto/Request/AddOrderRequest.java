package com.oekrem.SpringMVCBackEnd.Dto.Request;

import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Models.Payment;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddOrderRequest {

    private Long userId;
    private List<OrderDetail> orderDetailList;
    private Payment payment;
    private LocalDate date;
    private Double total;

    public AddOrderRequest() {
    }

    public AddOrderRequest(Long userId, List<OrderDetail> orderDetailList, Payment payment, LocalDate date, Double total) {
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

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
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
