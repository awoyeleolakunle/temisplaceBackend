package com.dansaki.com.temisplacebackend.services.orders.CreateOrder;


import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.models.OrderItemDetails;
import com.dansaki.com.temisplacebackend.dtos.request.OrderItemRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.services.orders.orderItemDetails.OrderItemDetailsService;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateOrderServiceImp implements CreateOrderService{

    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemDetailsService orderItemDetailsService;
    @Override
    public ApiResponse createOrder(OrderRequest orderRequest) {

        List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();

        for (OrderItemRequest orderItemRequest: orderRequest.getOrderItemRequestList()) {
        Item item =   itemService.findById(orderItemRequest.getItemId()).orElseThrow();
        OrderItemDetails orderItemDetails = new OrderItemDetails();
        orderItemDetails.setItems(item);
        orderItemDetails.setQuantity(orderItemRequest.getQuantity());
        orderItemDetails.setSubTotal(orderItemRequest.getSubTotal());
        OrderItemDetails savedOrderItemDetails = orderItemDetailsService.save(orderItemDetails);
        orderItemDetailsList.add(savedOrderItemDetails);
        }


        Orders newOrder = new Orders();
        newOrder.setOrderItemDetailsList(orderItemDetailsList);
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemDetails orderItemDetails : newOrder.getOrderItemDetailsList()) {
            total = total.add(orderItemDetails.getSubTotal());
        }
        newOrder.setTotal(total);
        newOrder.setUnitName(UnitName.valueOf(orderRequest.getUnitName()));
        newOrder.setOrderedTime(LocalDateTime.now());
        newOrder.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderService.save(newOrder);


        return GenerateApiResponse.createdResponse(newOrder);
    }


}