package com.dansaki.com.temisplacebackend.services.revenue.totalRevenue;


import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TotalMonthlyRevenueServiceImp implements TotalMonthlyRevenueService {

        private OrderService orderService;

        @Override
        public BigDecimal calculateMonthlyRevenue () {

            BigDecimal monthlyRevenue = BigDecimal.ZERO;
            Set<Orders> setOfMonthlyCompletedOrders = new HashSet<>();
            List<Orders> listOfCompletedOrders = orderService.findTotalCompletedOrder();


            LocalDateTime firstDayOfMonth = LocalDateTime.now().withDayOfMonth(1);
            LocalDateTime firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);

            for (Orders order : listOfCompletedOrders) {
                if (order.getOrderedTime().isEqual(firstDayOfMonth) || order.getOrderedTime().isAfter(firstDayOfMonth)
                        && order.getOrderedTime().isBefore(firstDayOfNextMonth)) {
                    setOfMonthlyCompletedOrders.add(order);
                }
            }

            for (Orders completedOrders : setOfMonthlyCompletedOrders) {
                monthlyRevenue = monthlyRevenue.add(completedOrders.getTotal());
            }
            return monthlyRevenue;
        }
}