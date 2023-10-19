package com.dansaki.com.temisplacebackend.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
    private Long onlineOrders;
    private Long instoreOrders;
    private double completedPercentage;
    private double cancelledPercentage;
    private double activePercentage;
    private double instorePercentage;
    private double onlinePercentage;
    private double revenuePercentage;
    private List<UnitMonthlyRevenueResponse> listOfAllUnitsWithMonthlyRevenue;
}
