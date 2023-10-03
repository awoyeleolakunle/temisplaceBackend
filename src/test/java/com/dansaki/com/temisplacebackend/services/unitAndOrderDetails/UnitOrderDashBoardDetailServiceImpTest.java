package com.dansaki.com.temisplacebackend.services.unitAndOrderDetails;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.enums.OrderFrom;
import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import com.dansaki.com.temisplacebackend.dtos.request.OrderItemRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class UnitOrderDashBoardDetailServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderRepository orderRepository;
    @Test
    void fetchTodayUnitOrderDetails() throws Exception {


        Item item = new Item();
        var savedItem = itemService.save(item);
        Item item1 = new Item();
        item1.setItemCategory(ItemCategory.valueOf("DRINKS"));

        var secondSavedItem = itemService.save(item1);
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
        orderRequest.setOrderFrom("ONLINE");
        orderRequest.setTotal(900L);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());



        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());






        Long orderId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/orderCompletion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderId", String.valueOf(orderId)))
                .andExpect(MockMvcResultMatchers.status().isOk());




        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/orderCancellation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderId", String.valueOf(orderId)))
                .andExpect(MockMvcResultMatchers.status().is(200));




        Orders orders = new Orders();
        orders.setUnitName(UnitName.valueOf("LONDON"));
        orders.setOrderedTime(LocalDateTime.parse("2023-10-02 10:00 AM", DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a", Locale.ENGLISH)));
        orders.setOrderStatus(OrderStatus.COMPLETED);
        orders.setTotal(BigDecimal.valueOf(500));
        orders.setOrderFrom(OrderFrom.ONLINE);
        orderRepository.save(orders);


        String unitName = "LONDON";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/unitOrderDashBoardDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("unitName", unitName))
                .andExpect(MockMvcResultMatchers.status().is(200));

//
//       Long order_Id = 2L;
//
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/orderCancellation")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("orderId", String.valueOf(order_Id)))
//                .andExpect(MockMvcResultMatchers.status().is(200));
//
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/orderCompletion")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .queryParam("orderId", String.valueOf(order_Id)))
//                .andExpect(MockMvcResultMatchers.status().isOk());

//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/dashBoardInfo")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}