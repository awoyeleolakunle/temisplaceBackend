package com.dansaki.com.temisplacebackend.data.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingInformation {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String countryName;
    private String district;
    private String homeNumber;
    private String apartment;
    private String city;
    private String postCode;
    private String phoneNumber;
    private String emailAddress;
    private String orderNote;
}

