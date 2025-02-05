package com.oekrem.SpringMVCBackEnd.Dto.Response;

import com.oekrem.SpringMVCBackEnd.Models.enums.QuantityType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponse {
    private Long id;
    private Long productId;
    private QuantityType quantityType;
    private BigDecimal price;
    private Long orderId;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(Long id, Long productId, QuantityType quantityType, BigDecimal price, Long orderId) {
        this.id = id;
        this.productId = productId;
        this.quantityType = quantityType;
        this.price = price;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
