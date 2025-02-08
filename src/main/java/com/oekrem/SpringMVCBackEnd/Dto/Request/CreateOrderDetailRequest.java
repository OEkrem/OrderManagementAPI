package com.oekrem.SpringMVCBackEnd.Dto.Request;

import com.oekrem.SpringMVCBackEnd.Models.enums.QuantityType;

import java.math.BigDecimal;

public class CreateOrderDetailRequest {

    private Long productId;
    private QuantityType quantityType;
    private BigDecimal quantity;
    private BigDecimal price;

    public CreateOrderDetailRequest() {
    }

    public CreateOrderDetailRequest(Long productId, QuantityType quantityType, BigDecimal quantity, BigDecimal price) {
        this.productId = productId;
        this.quantityType = quantityType;
        this.quantity = quantity;
        this.price = price;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
