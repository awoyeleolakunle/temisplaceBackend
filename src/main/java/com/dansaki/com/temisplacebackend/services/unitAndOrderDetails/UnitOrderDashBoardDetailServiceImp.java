package com.dansaki.com.temisplacebackend.services.unitAndOrderDetails;

import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.dtos.response.UnitOrderDashBoardDetailResponse;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class UnitOrderDashBoardDetailServiceImp implements UnitOrderDashBoardDetailService{

    private final OrderService orderService;
    @Override
    public UnitOrderDashBoardDetailResponse fetchTodayUnitOrderDetails(String unitName) {

        BigDecimal unitDailyRevenue = calculateUnitDailyRevenue(unitName);
        int totalNumberOfTodayCompletedOrders = returnSizeOfUnitAllOrdersUnderAnOrderStatusForToday(unitName, OrderStatus.COMPLETED);
        double percentageDifferenceOfCompletedOrders = calculatePercentageDifferenceOfTodayAndPreviousDayOrdersUnderAnOrderStatus(unitName, OrderStatus.COMPLETED);
        int totalNumberOfTodayCancelledOrders = returnSizeOfUnitAllOrdersUnderAnOrderStatusForToday(unitName, OrderStatus.CANCELLED);
        double percentageDifferenceOfCancelledOrders = calculatePercentageDifferenceOfTodayAndPreviousDayOrdersUnderAnOrderStatus(unitName, OrderStatus.CANCELLED);
        int totalNumberOfTodayActiveOrders = returnSizeOfUnitAllOrdersUnderAnOrderStatusForToday(unitName, OrderStatus.ACTIVE);
        double percentageDifferenceOfActiveOrders = calculatePercentageDifferenceOfTodayAndPreviousDayOrdersUnderAnOrderStatus(unitName, OrderStatus.ACTIVE);


        System.out.println("I'm the daily unitRevenue " + unitDailyRevenue);
        System.out.println("I'm the total active orders " +totalNumberOfTodayActiveOrders);
        System.out.println("I'm the active percentage " + percentageDifferenceOfActiveOrders);
        System.out.println("I'm the total completed orders "+ totalNumberOfTodayCompletedOrders);
        System.out.println("I'm the completed percentage " + percentageDifferenceOfCompletedOrders);
        System.out.println("I'm the total cancelled order " + totalNumberOfTodayCancelledOrders);
        System.out.println("I'm the  cancelled percentage " + percentageDifferenceOfCancelledOrders);



        return UnitOrderDashBoardDetailResponse.builder()
                .revenue(unitDailyRevenue)
                .activeOrder(totalNumberOfTodayActiveOrders)
                .activePercentageDifference(percentageDifferenceOfActiveOrders)
                .completedOrder(totalNumberOfTodayCompletedOrders)
                .completedPercentageDifference(percentageDifferenceOfCompletedOrders)
                .cancelledOrder(totalNumberOfTodayCancelledOrders)
                .cancelledPercentageDifference(percentageDifferenceOfCancelledOrders)
                .build();
    }

    private BigDecimal calculateUnitDailyRevenue(String unitName) {
        List<Orders> listOfCompletedOrders = orderService.findAUnitAllOrdersUnderOrderStatusForToday(unitName, OrderStatus.COMPLETED);
       if(listOfCompletedOrders.isEmpty()){ return BigDecimal.ZERO;}
        return listOfCompletedOrders.stream()
                .map(orders -> orders.getTotal().add(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private double calculatePercentageDifferenceBetweenTodayOrderAndPreviousDayOrder(List<Orders> todayOrders, List<Orders> previousDayOrders){
        double numbersOfTodayOrder = todayOrders.size();
        double numbersOfPreviousDayOrders = previousDayOrders.size();
        return (numbersOfTodayOrder - numbersOfPreviousDayOrders)
                / numbersOfTodayOrder * 100;
    }

    private int returnSizeOfUnitAllOrdersUnderAnOrderStatusForToday(String unitName, OrderStatus orderStatus){
        List<Orders> listOfUnitAllOrderUnderAnOrderStatusForToday = orderService.findAUnitAllOrdersUnderOrderStatusForToday(unitName, orderStatus);
        return listOfUnitAllOrderUnderAnOrderStatusForToday.size();
    }

    private double calculatePercentageDifferenceOfTodayAndPreviousDayOrdersUnderAnOrderStatus(String unitName, OrderStatus orderStatus){

        List<Orders> listOfTodayOrders = orderService.findAUnitAllOrdersUnderOrderStatusForToday(unitName, orderStatus);
        List<Orders> listOfPreviousDayOrders = orderService.findAUnitAllOrdersUnderOrderStatusForThePreviousDay(unitName, orderStatus);
        return calculatePercentageDifferenceBetweenTodayOrderAndPreviousDayOrder(listOfTodayOrders, listOfPreviousDayOrders);

    }
}
