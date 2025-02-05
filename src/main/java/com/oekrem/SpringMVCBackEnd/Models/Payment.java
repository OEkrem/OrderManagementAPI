package com.oekrem.SpringMVCBackEnd.Models;

import com.oekrem.SpringMVCBackEnd.Models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.Models.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
public class Payment {

    @SequenceGenerator(name = "payment", sequenceName = "Payment_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment")
    private Long id;

    @Column(length = 255)
    private String description;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public Payment() {}

    public Payment(Long id, String description, BigDecimal amount, PaymentStatus paymentStatus, PaymentMethod paymentMethod, LocalDateTime date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod +
                ", date=" + date +
                '}';
    }
}
