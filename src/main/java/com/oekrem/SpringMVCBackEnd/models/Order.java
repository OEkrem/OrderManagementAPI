package com.oekrem.SpringMVCBackEnd.models;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<OrderDetail> orderDetail = new LinkedList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    //@Transient kullanılabilir belki
    private Double total; // aslında hesaplanabilir ancak tek sorguyla kolaylıkla ulaşılabilir olması faydalı diye yazdım

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
        if(!orderDetail.isEmpty()){
            orderDetail.forEach(d -> this.total += d.getPrice());
        }else{
            this.total = 0.0;
        }
        if(payment == null) {
            payment = Payment.builder()
                    .order(Order.this)
                    .paymentMethod(PaymentMethod.UNKNOWN)
                    .paymentStatus(PaymentStatus.PENDING)
                    .description("-")
                    .date(LocalDateTime.now())
                    .amount(this.total)
                    .build();
        }
    }

}
