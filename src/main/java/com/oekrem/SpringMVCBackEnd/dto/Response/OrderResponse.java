package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponse {

    private Long id;
    private Long userId;
    private LocalDate date;
    private Double total;

    public OrderResponse() {
    }

    public OrderResponse(Long id, Long userId, LocalDate date, Double total) {
        this.id = id;
        this.userId = userId;
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
        return "OrderResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
