package com.dansaki.com.temisplacebackend.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemRequest {
    private Long itemId;
    private int quantity;
    private BigDecimal subTotal;
}
