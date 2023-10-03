package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileUpdateRequest {
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String phoneNumber;
    private String userStatus;
    private String roles;
    private String postCode;
}
