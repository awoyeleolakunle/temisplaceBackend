package com.dansaki.com.temisplacebackend.services.revenue.totalRevenue;


import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.dtos.response.UnitMonthlyRevenueResponse;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
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
public class TotalMonthlyAndDailyRevenueServiceImp implements TotalMonthlyAndDailyRevenueService {

    private OrderService orderService;

    @Override
    public BigDecimal calculateDailyRevenue() {

        BigDecimal todayRevenue = BigDecimal.ZERO;
        Set<Orders> setOfTodayCompletedOrders = new HashSet<>();
        List<Orders> listOfTodayCompletedOrders = orderService.findAllTodayOrdersByOrdertStatus(OrderStatus.COMPLETED);


        LocalDateTime startOfToday = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime startOfNextDay = startOfToday.plusDays(1);

        for (Orders order : listOfTodayCompletedOrders) {
            if (order.getOrderedTime().isEqual(startOfToday) || order.getOrderedTime().isAfter(startOfToday)
                    && order.getOrderedTime().isBefore(startOfNextDay)) {
                setOfTodayCompletedOrders.add(order);
            }
        }

        for (Orders completedOrders : setOfTodayCompletedOrders) {
            todayRevenue = todayRevenue.add(completedOrders.getTotal());
        }
        return todayRevenue;
    }



    public BigDecimal calculatePreviousDayRevenue() {

        BigDecimal previousDayRevenue = BigDecimal.ZERO;

        List<Orders> listOfPreviousDayCompletedOrders = orderService.findAllPreviousDayOrdersByOrderStatus(OrderStatus.COMPLETED);

        for (Orders completedOrders : listOfPreviousDayCompletedOrders) {
            previousDayRevenue = previousDayRevenue.add(completedOrders.getTotal());
        }
        return previousDayRevenue;
    }

    @Override
    public List<UnitMonthlyRevenueResponse> calculateEachUnitMonthlyRevenueAndPutAllOfThemInAList() {

        List<UnitMonthlyRevenueResponse> listOfUnitMonthlyRevenueAndUnitNames = new ArrayList<>();

        for (UnitName unitName : UnitName.values()) {

            List<Orders> listOfCurrentMonthCompletedOrdersInAUnit = orderService.findAUnitMonthlyOrdersUnderOrderStatus(String.valueOf(unitName), OrderStatus.COMPLETED);

            BigDecimal unitRevenue = listOfCurrentMonthCompletedOrdersInAUnit.stream().map(orders -> orders.getTotal().add(BigDecimal.TWO)).reduce(BigDecimal.ZERO, BigDecimal::add);

            UnitMonthlyRevenueResponse unitMonthlyRevenueResponse = new UnitMonthlyRevenueResponse();
            unitMonthlyRevenueResponse.setUnitName(String.valueOf(unitName));
            unitMonthlyRevenueResponse.setUnitCurrentMonthRevenue(unitRevenue);
            listOfUnitMonthlyRevenueAndUnitNames.add(unitMonthlyRevenueResponse);
        }
        return listOfUnitMonthlyRevenueAndUnitNames;
    }
}