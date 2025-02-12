package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponse {

    private Long id;
    private Long productId;
    private QuantityType quantityType;
    private BigDecimal quantity;
    private BigDecimal price;
    private Long orderId;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(Long id, Long productId, QuantityType quantityType, BigDecimal quantity, BigDecimal price, Long orderId) {
        this.id = id;
        this.productId = productId;
        this.quantityType = quantityType;
        this.quantity = quantity;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailResponse{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantityType=" + quantityType +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderId=" + orderId +
                '}';
    }
}
