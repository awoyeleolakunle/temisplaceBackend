package com.dansaki.com.temisplacebackend.dtos.request;


import com.dansaki.com.temisplacebackend.data.models.BillingInformation;
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
    private Long total;
    private String unitName;
    private String orderFrom;
    private String paymentType;
    private int numberOfAllItemQuantityOrdered;
    private BillingInformation billingInformation;
}
