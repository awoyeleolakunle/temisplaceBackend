package com.dansaki.com.temisplacebackend.controllers.ordersControllers.completeOrder;


import com.dansaki.com.temisplacebackend.services.orders.orderStatus.OrderStatusService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/temisplace/Orders/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class OrderCompletionController {


    private final OrderStatusService orderStatusService;

    @PostMapping("orderCompletion")
    public ResponseEntity<ApiResponse> completeOrder(@RequestParam Long orderId){
        return new ResponseEntity<>(orderStatusService.completeOrder(orderId), HttpStatus.OK);
    }
}
