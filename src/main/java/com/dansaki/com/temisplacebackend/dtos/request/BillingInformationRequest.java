package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingInformationRequest {

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
