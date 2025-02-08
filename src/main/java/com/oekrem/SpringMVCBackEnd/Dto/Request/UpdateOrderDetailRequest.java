package com.oekrem.SpringMVCBackEnd.Dto.Request;

import com.oekrem.SpringMVCBackEnd.Models.enums.QuantityType;

import java.math.BigDecimal;

public class UpdateOrderDetailRequest {

    private Long id;
    private Long productId;
    private QuantityType quantityType;
    private BigDecimal quantity;
    private BigDecimal price;
    //private Long orderId; // bu bilgi pathvariable olarak alınacak

    public UpdateOrderDetailRequest() {
    }

    public UpdateOrderDetailRequest(Long id, Long productId, QuantityType quantityType, BigDecimal quantity, BigDecimal price) {
        this.id = id;
        this.productId = productId;
        this.quantityType = quantityType;
        this.quantity = quantity;
        this.price = price;
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

    @Override
    public String toString() {
        return "UpdateOrderDetailRequest{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantityType=" + quantityType +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
