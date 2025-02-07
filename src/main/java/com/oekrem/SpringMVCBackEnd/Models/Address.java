package com.oekrem.SpringMVCBackEnd.Models;

import jakarta.persistence.*;


@Entity
@Table(name = "addresses")
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

    public Address() {}

    public Address(Long id, String name, Integer doorNumber, Integer floor, String buildingNumber, String street, String city, String country, User user) {
        this.id = id;
        this.name = name;
        this.doorNumber = doorNumber;
        this.floor = floor;
        this.buildingNumber = buildingNumber;
        this.street = street;
        this.city = city;
        this.country = country;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(Integer doorNumber) {
        this.doorNumber = doorNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doorNumber=" + doorNumber +
                ", floor=" + floor +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", user=" + user +
                '}';
    }
}
