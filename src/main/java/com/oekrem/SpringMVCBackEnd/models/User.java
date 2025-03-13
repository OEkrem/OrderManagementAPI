package com.oekrem.SpringMVCBackEnd.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

//@Data  --> içerisinde toString metodu içerdiği için refreshToken ile sonsuz döngüye girdi kanka
@Getter
@Setter
@EqualsAndHashCode

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private RefreshToken refreshToken;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

}
