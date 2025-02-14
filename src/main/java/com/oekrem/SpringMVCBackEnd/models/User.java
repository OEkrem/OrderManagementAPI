package com.oekrem.SpringMVCBackEnd.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @SequenceGenerator(name = "user", sequenceName = "User_Id_Seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    private Long id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user", orphanRemoval = true)
    private List<Address> addresses = new LinkedList<>();
    // bir kullanıcının birden fazla tanımlı adresi olabilir

}
