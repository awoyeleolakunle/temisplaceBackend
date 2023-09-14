package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    List<OrderItemRequest> orderItemRequestList;
    private BigDecimal total;
    private String unitName;
}
