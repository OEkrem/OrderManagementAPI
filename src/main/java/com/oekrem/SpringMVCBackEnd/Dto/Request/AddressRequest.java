package com.oekrem.SpringMVCBackEnd.Dto.Request;

import lombok.Data;

@Data
public class AddressRequest {

    private String name;
    private Integer doorNumber;
    private Integer floor;
    private String buildingNumber;
    private String street;
    private String city;
    private String country;

    public AddressRequest() {
    }

    public AddressRequest(String name, Integer doorNumber, Integer floor, String buildingNumber, String street, String city, String country) {
        this.name = name;
        this.doorNumber = doorNumber;
        this.floor = floor;
        this.buildingNumber = buildingNumber;
        this.street = street;
        this.city = city;
        this.country = country;
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

    @Override
    public String toString() {
        return "AddressRequest{" +
                "name='" + name + '\'' +
                ", doorNumber=" + doorNumber +
                ", floor=" + floor +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
