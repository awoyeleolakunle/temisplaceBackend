package com.dansaki.com.temisplacebackend.data.models;


import com.dansaki.com.temisplacebackend.data.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String country;
    private String city;
    private String postCode;
    private String address;
    private LocalDate registrationDate;
    private LocalTime registrationTime;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
   // @CollectionTable(name = "role")
    private Set<Roles> roles;
    @OneToMany
    @JoinColumn(name = "customer_Id")
    private List<Orders> orders;

}
