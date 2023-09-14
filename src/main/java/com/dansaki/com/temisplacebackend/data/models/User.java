package com.dansaki.com.temisplacebackend.data.models;


import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
   // @CollectionTable(name = "role")
    private Set<Roles> roles;
    @OneToMany
    @JoinColumn(name = "customer_Id")
    private List<Orders> orders;

}
