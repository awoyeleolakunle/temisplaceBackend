package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;

import java.util.List;
import java.util.Set;

public interface OrderService {

    Orders save(Orders order);


    List<Orders> findTotalCompletedOrder();

    List<Orders> findTotalCancelledOrders();

    List<Orders> findAllLondonUnitCompletedOrders(UnitName unitName, OrderStatus orderStatus);
}
