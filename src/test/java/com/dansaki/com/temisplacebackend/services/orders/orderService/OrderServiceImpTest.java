package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.*;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.ItemRepository;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import com.dansaki.com.temisplacebackend.dtos.request.*;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.services.itemPriceAndSize.ItemPriceAndSizeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
class OrderServiceImpTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderRepository orderRepository;

    private ItemPriceAndSizeService itemPriceAndSizeService;

    @Test
    void findAUnitAllOrdersUnderOrderStatus() throws Exception {


        ItemPriceAndSize itemPriceAndSize = new ItemPriceAndSize();
        itemPriceAndSize.setPrice(BigDecimal.valueOf(200));
        itemPriceAndSize.setSize("400ml");
        var savedItemPriceAndSizes = itemPriceAndSizeService.save(itemPriceAndSize);

        ItemPriceAndSize itemPriceAndSize1 = new ItemPriceAndSize();
        itemPriceAndSize1.setSize("300ml");
        itemPriceAndSize1.setPrice(BigDecimal.valueOf(500));
       var savedItemPriceAndSizes1= itemPriceAndSizeService.save(itemPriceAndSize1);
        List<ItemPriceAndSize> list = new ArrayList<>();
        list.add(savedItemPriceAndSizes);
        list.add(savedItemPriceAndSizes1);
        Item item = new Item();
        item.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        item.setItemTitle("Amala");
        item.setItemCategory(ItemCategory.SWALLOW);
        item.setItemPriceAndSize(new ArrayList<>(list));
        var savedItem = itemService.save(item);
        Item item1 = new Item();
        item1.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item1.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        item1.setItemPriceAndSize(new ArrayList<>(list));
        item1.setItemTitle("Coke");
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
        orderRequest.setUnitName("SUNDERLAND");
        orderRequest.setOrderFrom("ONLINE");
        orderRequest.setTotal(900L);
        orderRequest.setPaymentType("CASH");



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

        UnitOrderUnderOrderStatusRequest unitOrderUnderOrderStatusRequest = new UnitOrderUnderOrderStatusRequest();
        unitOrderUnderOrderStatusRequest.setUnitName("LONDON");
        unitOrderUnderOrderStatusRequest.setOrderStatus("COMPLETED");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/unitAllDailyOrdersUnderOrderStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unitOrderUnderOrderStatusRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}