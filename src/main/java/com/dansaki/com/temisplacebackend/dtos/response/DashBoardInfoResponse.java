package com.dansaki.com.temisplacebackend.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardInfoResponse {
    private Long totalCustomers;
    private Double newCustomerPercentage;
    private Long completedOrders;
    private Long cancelledOrders;
    private BigDecimal totalRevenue;
}
