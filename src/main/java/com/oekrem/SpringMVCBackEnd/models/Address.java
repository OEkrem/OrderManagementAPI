package com.oekrem.SpringMVCBackEnd.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "addresses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @SequenceGenerator(name = "address", allocationSize = 1, sequenceName = "address_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "doorNumber", length = 5)
    private Integer doorNumber;

    @Column(name = "floor", length = 5)
    private Integer floor;

    @Column(name = "buildingNumber", length = 15)
    private String buildingNumber;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country", length = 30)
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key
    private User user;

}
