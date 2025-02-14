package com.oekrem.SpringMVCBackEnd.models;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private QuantityType quantityType;

    private BigDecimal quantity;

    private BigDecimal price; // ürünfiyatı x quantity cevabı olacak aslında buradan da oluşalabiliyor olalım diye yazdım.

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
