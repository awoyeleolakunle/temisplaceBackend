package com.dansaki.com.temisplacebackend.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ItemPriceAndSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    private String size;
    private List<String> listOfUnitsAvailable =
            List.of(new String[]{ "SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"});
    private Boolean isAvailable = true;
}

