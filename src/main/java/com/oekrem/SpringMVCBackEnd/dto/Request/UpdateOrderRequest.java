package com.oekrem.SpringMVCBackEnd.dto.Request;

import java.time.LocalDate;

public class UpdateOrderRequest {

    private Long id;
    private LocalDate date;
    private Double total;

    public UpdateOrderRequest() {
    }

    public UpdateOrderRequest(Long id, LocalDate date, Double total) {
        this.id = id;
        this.date = date;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
