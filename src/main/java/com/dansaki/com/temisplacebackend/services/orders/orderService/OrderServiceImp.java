package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor

public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> findTotalCompletedOrder() {
        return orderRepository.findAllByOrderStatus(OrderStatus.COMPLETED);
    }

    @Override
    public List<Orders> findTotalCancelledOrders() {
        return orderRepository.findAllByOrderStatus(OrderStatus.CANCELLED);
    }

    @Override
    public List<Orders> findAllLondonUnitCompletedOrders(UnitName unitName, OrderStatus orderStatus) {
        return orderRepository.findAllByUnitNameAndOrderStatus(unitName, orderStatus);
    }
}
