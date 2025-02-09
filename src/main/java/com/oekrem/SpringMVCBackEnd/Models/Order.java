package com.oekrem.SpringMVCBackEnd.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    //@Transient kullanılabilir belki
    private Double total; // aslında hesaplanabilir ancak tek sorguyla kolaylıkla ulaşılabilir olması faydalı diye yazdım

    public Order() {}

    public Order(Long id, User user, List<OrderDetail> orderDetail, Payment payment, LocalDate date, Double total) {
        this.id = id;
        this.user = user;
        this.orderDetail = orderDetail;
        this.payment = payment;
        this.date = date;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderDetail=" + orderDetail +
                ", payment=" + payment +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
