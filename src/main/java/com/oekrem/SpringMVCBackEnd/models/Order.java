package com.oekrem.SpringMVCBackEnd.models;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @SequenceGenerator(name = "order", sequenceName = "Order_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order")
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new LinkedList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Payment payment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double total; // aslında hesaplanabilir ancak tek sorguyla kolaylıkla ulaşılabilir olması faydalı diye yazdım

    @PrePersist
    protected void onCreate() {
        //System.out.println("Order created");
        this.date = LocalDate.now();
        if(orderDetails != null && !orderDetails.isEmpty()) {
            orderDetails.forEach(d -> this.total += d.getPrice());
        }else{
            this.total = 0.0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        //System.out.println("Order updated");
        if(orderDetails != null && !orderDetails.isEmpty()){
            orderDetails.forEach(d -> this.total += d.getPrice());
        }else{
            this.total = 0.0;
        }
        if(payment != null) {
            payment.setAmount(this.total);
        }/*else payment.setAmount(this.total);*/
    }
}
