package com.oekrem.SpringMVCBackEnd.Dto.Response;

import lombok.*;

import java.util.Set;

@Data
public class UserResponse {

    private int id;
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<AddressResponse> addresses;

    public UserResponse() {
    }

    public UserResponse(int id, String username, String password, String firstName, String lastName, String email, String phone, Set<AddressResponse> addresses) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressResponse> addresses) {
        this.addresses = addresses;
    }
}
