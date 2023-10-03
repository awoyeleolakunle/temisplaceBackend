package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;

import java.util.List;

public interface OrderService {

    Orders save(Orders order);


    List<Orders> findTotalCompletedOrder();

    List<Orders> findTotalCancelledOrders();

    List<Orders> findAllLondonUnitCompletedOrders(UnitName unitName, OrderStatus orderStatus);

    List<Orders> findAllUnitCompletedOrdersForThePreviousMonth(String unitName);

    List<Orders> findAllUnitCompletedOrdersForTheCurrentMonth(String unitName);

    List<Orders> findAUnitAllOrdersUnderOrderStatusForToday(String unitName, OrderStatus orderStatus);

    List<Orders> findAUnitAllOrdersUnderOrderStatusForThePreviousDay(String unitName, OrderStatus orderStatus);

    //List<Orders> findAUnitAllCancelledOrdersForToday(String unitName);

}
