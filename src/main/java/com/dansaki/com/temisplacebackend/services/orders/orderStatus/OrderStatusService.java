package com.dansaki.com.temisplacebackend.services.orders.orderStatus;

import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface OrderStatusService {

    ApiResponse completeOrder(Long orderId);

    ApiResponse cancelOrder(Long orderId);
}
