package com.oekrem.SpringMVCBackEnd.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @SequenceGenerator(name = "order", sequenceName = "Order_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Payment payment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    //@Transient kullanılabilir belki
    private Double total; // aslında hesaplanabilir ancak tek sorguyla kolaylıkla ulaşılabilir olması faydalı diye yazdım

    public Order() {}

    public Order(Long id, User user, Set<OrderDetail> orderDetail, Payment payment, LocalDate date, Double total) {
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

    public Set<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(Set<OrderDetail> orderDetail) {
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
