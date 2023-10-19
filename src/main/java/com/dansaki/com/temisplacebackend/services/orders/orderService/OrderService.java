package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.OrderFrom;
import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.dtos.request.UnitOrderUnderOrderStatusRequest;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Orders save(Orders order);


    List<Orders> findTotalMonthlyCompletedOrder();

    Optional<Orders> findOrderById(Long orderId);

    List<Orders> findTotalMonthlyCancelledOrders();

    List<Orders> findTotalMonthlyInstoreOrders();

    List<Orders> findTotalMonthlyOnlineOrders();

    List<Orders> findAllTodayOrdersByOrdertStatus(OrderStatus orderStatus);

    List<Orders> findAllLondonUnitCompletedOrders(UnitName unitName, OrderStatus orderStatus);

    List<Orders> findAllUnitCompletedOrdersForThePreviousMonth(String unitName);

    List<Orders> findAllUnitCompletedOrdersForTheCurrentMonth(String unitName);

    List<Orders> findAUnitAllOrdersUnderOrderStatusForToday(String unitName, OrderStatus orderStatus);

    List<Orders> findAUnitAllOrdersUnderOrderStatusForThePreviousDay(String unitName, OrderStatus orderStatus);

    List<Orders> findAUnitAllDailyOrdersUnderOrderStatus(UnitOrderUnderOrderStatusRequest unitOrderUnderOrderStatusRequest);

    List<Orders> findAUnitMonthlyOrdersUnderOrderStatus(String UnitName, OrderStatus orderStatus);

    List<Orders> findAllTodayOrdersUnderOrderFrom(OrderFrom orderFrom);

    List<Orders> findAllPreviousDayOrdersUnderOrderFrom(OrderFrom orderFrom);

    List<Orders> findAllPreviousDayOrdersByOrderStatus(OrderStatus orderStatus);


    //List<Orders> findAUnitAllCancelledOrdersForToday(String unitName);

}
