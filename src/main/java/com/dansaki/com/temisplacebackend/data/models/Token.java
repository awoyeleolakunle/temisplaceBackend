package com.dansaki.com.temisplacebackend.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
 @Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userEmailAddress;
    private String jwt;
    private boolean isRevoked;
    private boolean isExpired;
}
