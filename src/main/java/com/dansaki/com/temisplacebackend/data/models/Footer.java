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
public class Footer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
