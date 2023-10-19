package com.dansaki.com.temisplacebackend.services.unitAndOrderDetails.orderItemDetails;

import com.dansaki.com.temisplacebackend.data.models.OrderItemDetails;
import com.dansaki.com.temisplacebackend.data.repositories.OrderItemDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderItemDetailsServiceImp implements OrderItemDetailsService{

    private final OrderItemDetailsRepository orderItemDetailsRepository;

    @Override
    public OrderItemDetails save(OrderItemDetails orderItemDetails) {
        return orderItemDetailsRepository.save(orderItemDetails);
    }
}
