package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationRequest {
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String phoneNumber;
}
