package com.dansaki.com.temisplacebackend.services.orders.manageActiveOrderStatusAndOrderStatus;


import com.dansaki.com.temisplacebackend.data.enums.ActiveOrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.dtos.request.ActiveOrderStatusAndOrderStatusRequest;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ActiveOrderStatusAndOrderStatusManagementServiceImp implements ActiveOrderStatusAndOrderStatusManagementService {
    private final OrderService orderService;
    @Override
    public ApiResponse manageActiveOrderStatusAndOrderStatus(ActiveOrderStatusAndOrderStatusRequest activeOrderStatusAndOrderStatusRequest) {


        Optional<Orders> foundOrder = orderService.findOrderById(activeOrderStatusAndOrderStatusRequest.getOrderId());
       if(foundOrder.isEmpty()) return GenerateApiResponse.noSuchOrder(GenerateApiResponse.ORDER_NOT_FOUND);

       if( foundOrder.get().getOrderStatus().equals(OrderStatus.CANCELLED)|| foundOrder.get().getOrderStatus().equals(OrderStatus.COMPLETED)){
                    return GenerateApiResponse.alreadyCreated(GenerateApiResponse.ORDER_ALREADY_CANCELLED);
       }

       if(activeOrderStatusAndOrderStatusRequest.getActiveOrderStatusOrOrderStatus().equals(OrderStatus.CANCELLED.name())
       || activeOrderStatusAndOrderStatusRequest.getActiveOrderStatusOrOrderStatus().equals(OrderStatus.COMPLETED.name())){
           foundOrder.get().setOrderStatus(OrderStatus.valueOf(activeOrderStatusAndOrderStatusRequest.getActiveOrderStatusOrOrderStatus()));
           foundOrder.get().setActiveOrderStatus(ActiveOrderStatus.DELIVERED);
           orderService.save(foundOrder.get());
       }
       if(!foundOrder.get().getActiveOrderStatus().equals(ActiveOrderStatus.DELIVERED)) {
           foundOrder.get().setActiveOrderStatus(ActiveOrderStatus.valueOf(activeOrderStatusAndOrderStatusRequest.getActiveOrderStatusOrOrderStatus()));
        orderService.save(foundOrder.get());
        return GenerateApiResponse.updateSuccessful(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
       }
        return GenerateApiResponse.incorrectDetails(GenerateApiResponse.INCORRECT_DETAILS);
    }
}
