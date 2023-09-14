package com.dansaki.com.temisplacebackend.services.orders.orderStatus;


import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderStatusServiceImp implements OrderStatusService {

    private final OrderRepository orderRepository;

    @Override
    public ApiResponse completeOrder(Long orderId) {
        if(isNotPresent(orderId)) return GenerateApiResponse.noSuchOrder(GenerateApiResponse.ORDER_NOT_FOUND);
        if(isCancelled(orderId)) return GenerateApiResponse.alreadyCancelled(GenerateApiResponse.ORDER_ALREADY_CANCELLED);
        Orders order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        return GenerateApiResponse.UpdateStatus(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
    }

    @Override
    public ApiResponse cancelOrder(Long orderId) {
        if(isNotPresent(orderId)) return GenerateApiResponse.noSuchOrder(GenerateApiResponse.ORDER_NOT_FOUND);
        if(isCompleted(orderId)) return GenerateApiResponse.alreadyCompleted(GenerateApiResponse.ORDER_ALREADY_COMPLETED);
        Orders order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return GenerateApiResponse.UpdateStatus(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
    }

    private boolean isNotPresent(Long orderId) {
        return !orderRepository.existsById(orderId);
    }

    private boolean isCancelled(Long orderId) {
        Optional<Orders> order =  orderRepository.findById(orderId);
        return order.map(orders -> orders.getOrderStatus().equals(OrderStatus.CANCELLED)).orElse(false);
    }

    private boolean isCompleted(Long orderId) {
        Optional<Orders> order =  orderRepository.findById(orderId);
        return order.map(orders -> orders.getOrderStatus().equals(OrderStatus.COMPLETED)).orElse(false);
    }

}
