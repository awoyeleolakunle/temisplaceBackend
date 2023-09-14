package com.dansaki.com.temisplacebackend.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemDetailsId;


    @ManyToOne
    private Item items;
    private int quantity;
    private BigDecimal subTotal = BigDecimal.ZERO;
}