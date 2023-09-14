package com.dansaki.com.temisplacebackend.services.revenue.unitRevenue;


import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.services.revenue.totalRevenue.TotalMonthlyRevenueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TotalUnitMonthlyRevenueServiceImp implements TotalUnitMonthlyRevenueService{

    private final OrderService orderService;

    private final TotalMonthlyRevenueService totalMonthlyRevenueService;

    private List<BigDecimal> listOfUnitRevenue;


    @Override
    public BigDecimal calculateUnitMonthlyRevenue() {


        List<String> unitNames = List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"});

        List<Orders> listOfUnitOrders;
        for (String unitName: unitNames) {
           listOfUnitOrders = orderService.findAllLondonUnitCompletedOrders(UnitName.valueOf(unitName), OrderStatus.COMPLETED);
        }

        return null;
    }

    private BigDecimal calculateMonthlyRevenue(List<Orders> listOfUnitOrders){

        Set<Orders> setOfMonthlyCompletedOrders = new HashSet<>();

        LocalDateTime firstDayOfMonth = LocalDateTime.now().withDayOfMonth(1);
        LocalDateTime firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);

        for (Orders order: listOfUnitOrders) {
            if (order.getOrderedTime().isEqual(firstDayOfMonth) || order.getOrderedTime().isAfter(firstDayOfMonth)
                    && order.getOrderedTime().isBefore(firstDayOfNextMonth)) {
                setOfMonthlyCompletedOrders.add(order);
            }
        }

        for (Orders order: setOfMonthlyCompletedOrders) {
            BigDecimal unitMonthlyRevenue = BigDecimal.ZERO;
            unitMonthlyRevenue = unitMonthlyRevenue.add(order.getTotal());
            listOfUnitRevenue.add(unitMonthlyRevenue);
        }
        return null;
    }

}
