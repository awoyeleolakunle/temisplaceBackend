package com.dansaki.com.temisplacebackend.services.orders.CreateOrder;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.repositories.ItemRepository;
import com.dansaki.com.temisplacebackend.dtos.request.OrderItemRequest;

import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
class CreateOrderServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CreateOrderService createOrderService;

    @Test
     public void testThatAUserCanMakeOrder() throws Exception {
        Item item = new Item();
      var savedItem = itemRepository.save(item);
         Item item1 = new Item();
         item1.setItemCategory(ItemCategory.valueOf("DRINKS"));

         var secondSavedItem = itemRepository.save(item1);
      List<OrderItemRequest> orderRequestList = new ArrayList<>();

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemId(savedItem.getItemId());
        orderItemRequest.setQuantity(5);
        orderItemRequest.setSubTotal(BigDecimal.valueOf(500));


        OrderItemRequest orderItemRequest1 = new OrderItemRequest();
        orderItemRequest1.setItemId(secondSavedItem.getItemId());
        orderItemRequest1.setQuantity(4);
        orderItemRequest1.setSubTotal(BigDecimal.valueOf(400));

        orderRequestList.add(orderItemRequest);
        orderRequestList.add(orderItemRequest1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderItemRequestList(new ArrayList<>(orderRequestList));
        orderRequest.setUnitName("LONDON");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());



        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}