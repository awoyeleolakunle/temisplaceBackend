package com.dansaki.com.temisplacebackend.services.orders.CreateOrder;


import com.dansaki.com.temisplacebackend.data.enums.*;
import com.dansaki.com.temisplacebackend.data.models.BillingInformation;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.models.OrderItemDetails;
import com.dansaki.com.temisplacebackend.data.repositories.BillingInformationRepository;
import com.dansaki.com.temisplacebackend.dtos.request.BillingInformationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderItemRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.dansaki.com.temisplacebackend.services.billingInformation.BillingInformationService;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.services.unitAndOrderDetails.orderItemDetails.OrderItemDetailsService;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateOrderServiceImp implements CreateOrderService{

    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemDetailsService orderItemDetailsService;

   // private final BillingInformationService billingInformationService;
    private final BillingInformationRepository billingInformationRepository;

    private final ModelMapper modelMapper;
    @Override
    public ApiResponse createOrder(OrderRequest orderRequest) {

        System.out.println("I'm in here ");

        BillingInformation billingInformation = orderRequest.getBillingInformation();
        BillingInformation savedBillingInformation =  billingInformationRepository.save(billingInformation);
        System.out.println("I'm teh saved billing information email " + savedBillingInformation.getEmailAddress());
        System.out.println("I'm teh saved billing information city " + savedBillingInformation.getCity());
        System.out.println("I'm teh saved billing information first name " + savedBillingInformation.getFirstName());
        System.out.println("I'm teh saved billing information last name " + savedBillingInformation.getLastName());




        List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();

        for (OrderItemRequest orderItemRequest: orderRequest.getOrderItemRequestList()) {
        Item item =   itemService.findById(orderItemRequest.getItemId()).orElseThrow();
        OrderItemDetails orderItemDetails = new OrderItemDetails();
        orderItemDetails.setItem(item);
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
        newOrder.setTotal(BigDecimal.valueOf(orderRequest.getTotal()));
        newOrder.setUnitName(UnitName.valueOf(orderRequest.getUnitName()));
        newOrder.setOrderedTime(LocalDateTime.now());
        newOrder.setOrderStatus(OrderStatus.ACTIVE);
        newOrder.setActiveOrderStatus(ActiveOrderStatus.ACCEPT_AND_PROCESSING);
        newOrder.setPaymentType(PaymentType.valueOf(orderRequest.getPaymentType().toUpperCase()));
        newOrder.setOrderFrom(OrderFrom.valueOf(orderRequest.getOrderFrom()));
        newOrder.setNumberOfAllItemQuantityOrdered(orderRequest.getNumberOfAllItemQuantityOrdered());
        newOrder.setBillingInformation(savedBillingInformation);

        if(orderRequest.getOrderFrom().equals(OrderFrom.INSTORE.name())){

            newOrder.setOrderStatus(OrderStatus.COMPLETED);
        }
        orderService.save(newOrder);

        return GenerateApiResponse.createdResponse("YES");
    }

}
