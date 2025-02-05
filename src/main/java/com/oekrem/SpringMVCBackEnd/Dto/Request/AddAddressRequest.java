package com.oekrem.SpringMVCBackEnd.Dto.Request;

import lombok.Data;

@Data
public class AddAddressRequest {
    private String name;
    private Integer doorNumber;
    private Integer floor;
    private String buildingNumber;
    private String street;
    private String city;
    private String country;
    private Long userId;

    public AddAddressRequest() {
    }

    public AddAddressRequest(String name, Integer doorNumber, Integer floor, String buildingNumber, String street, String city, String country, Long userId) {
        this.name = name;
        this.doorNumber = doorNumber;
        this.floor = floor;
        this.buildingNumber = buildingNumber;
        this.street = street;
        this.city = city;
        this.country = country;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
