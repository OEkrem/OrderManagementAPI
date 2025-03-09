package com.oekrem.SpringMVCBackEnd.models;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {

    @SequenceGenerator(name = "payment", sequenceName = "Payment_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment")
    private Long id;

    @Column(length = 255)
    private String description;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
        this.paymentStatus = this.paymentStatus == null ? PaymentStatus.PENDING : this.paymentStatus;
        this.paymentMethod = this.paymentMethod == null ? PaymentMethod.UNKNOWN : this.paymentMethod;
    }

}
