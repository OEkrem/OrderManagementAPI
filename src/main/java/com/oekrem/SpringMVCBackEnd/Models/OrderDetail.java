package com.oekrem.SpringMVCBackEnd.Models;

import com.oekrem.SpringMVCBackEnd.Models.enums.QuantityType;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

    @SequenceGenerator(name = "orderDetails", sequenceName = "ODetails_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetails")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private QuantityType quantityType;

    private BigDecimal quantity;

    private BigDecimal price; // ürünfiyatı x quantity cevabı olacak aslında buradan da oluşalabiliyor olalım diye yazdım.

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderDetail() {}

    public OrderDetail(Long id, Product product, QuantityType quantityType, BigDecimal quantity, BigDecimal price, Order order) {
        this.id = id;
        this.product = product;
        this.quantityType = quantityType;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", product=" + product +
                ", quantityType=" + quantityType +
                ", quantity=" + quantity +
                ", price=" + price +
                ", order=" + order +
                '}';
    }
}
