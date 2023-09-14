package com.dansaki.com.temisplacebackend.services.orders.CreateOrder;

import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;



public interface CreateOrderService {
    ApiResponse createOrder(OrderRequest orderRequest);
}
