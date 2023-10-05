package com.dansaki.com.temisplacebackend.controllers.unitOrdersManagmentControllers;


import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.dtos.request.UnitOrderUnderOrderStatusRequest;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UnitOrdersManagementControllers {

    private final OrderService orderService;

    @PostMapping("unitAllOrdersUnderOrderStatus")
    public ResponseEntity<List<Orders>> fetchUnitAllOrdersUnderOrderStatus( @RequestBody UnitOrderUnderOrderStatusRequest unitOrderUnderOrderStatusRequest){
        return new ResponseEntity<>(orderService.findAUnitAllOrdersUnderOrderStatus(unitOrderUnderOrderStatusRequest), HttpStatus.OK);
    }
}
