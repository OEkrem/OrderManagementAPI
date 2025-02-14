package com.oekrem.SpringMVCBackEnd.models;

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

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<OrderDetail> orderDetail = new LinkedList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    //@Transient kullanılabilir belki
    private Double total; // aslında hesaplanabilir ancak tek sorguyla kolaylıkla ulaşılabilir olması faydalı diye yazdım

}
