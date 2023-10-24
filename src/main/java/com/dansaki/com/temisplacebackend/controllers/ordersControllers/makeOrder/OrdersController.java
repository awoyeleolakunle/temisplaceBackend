package com.dansaki.com.temisplacebackend.controllers.ordersControllers.makeOrder;


import com.dansaki.com.temisplacebackend.dtos.request.OrderItemRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.dansaki.com.temisplacebackend.services.orders.CreateOrder.CreateOrderService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/temisplace/Orders/")

public class OrdersController {

    private final CreateOrderService createOrderService;

    @PostMapping("makeOrder")
    public ResponseEntity<ApiResponse> makeOrder(@RequestBody OrderRequest orderRequest){

        System.out.println("I entered here");
        return new ResponseEntity<>(createOrderService.createOrder(orderRequest), HttpStatus.OK);
    }
}
