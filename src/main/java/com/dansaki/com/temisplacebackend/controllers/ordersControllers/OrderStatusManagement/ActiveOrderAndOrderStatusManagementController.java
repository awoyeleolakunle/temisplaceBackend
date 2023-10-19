package com.dansaki.com.temisplacebackend.controllers.ordersControllers.OrderStatusManagement;


import com.dansaki.com.temisplacebackend.dtos.request.ActiveOrderStatusAndOrderStatusRequest;
import com.dansaki.com.temisplacebackend.services.orders.manageActiveOrderStatusAndOrderStatus.ActiveOrderStatusAndOrderStatusManagementService;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ActiveOrderAndOrderStatusManagementController {

    private final ActiveOrderStatusAndOrderStatusManagementService activeOrderStatusAndOrderStatusManagementService;

    @PostMapping("activeOrderStatusAndOrderStatusManagement")

    public ResponseEntity<ApiResponse> manageActiveOrderStatusAndOrderStatus(@RequestBody ActiveOrderStatusAndOrderStatusRequest activeOrderStatusAndOrderStatusRequest){

        return new ResponseEntity<>(activeOrderStatusAndOrderStatusManagementService.manageActiveOrderStatusAndOrderStatus(activeOrderStatusAndOrderStatusRequest), HttpStatus.OK);
    }
}
