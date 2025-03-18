package com.oekrem.SpringMVCBackEnd.models;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

    @SequenceGenerator(name = "orderDetails", sequenceName = "ODetails_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetails")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // tabi ilerde belki değişebilir
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private QuantityType quantityType;

    private Integer quantity;

    private Double price; // ürünfiyatı x quantity cevabı olacak aslında buradan da oluşalabiliyor olalım diye yazdım.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @PrePersist
    protected void onCreate() {
        this.quantityType = this.quantityType == null ? QuantityType.UNKNOWN : this.quantityType;
        this.quantity = this.quantity == null ? 0 : this.quantity;
        this.price = this.product != null ? (this.product.getPrice()*this.quantity) : 0.0D;
    }

}
