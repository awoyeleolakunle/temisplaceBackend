package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FooterDetailsRequest {
    private String slogan;
    private String overview;
    private String telephone;
    private String facebook;
    private String twitter;
    private String instagram;
    private String linkedin;
    private String address;
    private String city;
    private String postCode;
    private String footerImgUrl;
}


