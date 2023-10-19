package com.dansaki.com.temisplacebackend.services.unitDashBoard.dashBoardDetails;

import com.dansaki.com.temisplacebackend.data.enums.*;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.ItemRepository;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderItemRequest;
import com.dansaki.com.temisplacebackend.dtos.request.OrderRequest;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
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


@SpringBootTest
@AutoConfigureMockMvc
class UnitDashBoardDetailServiceImpTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void testThatListOfItemCategoryInfoForUnitDashBoardCanBeFetched() throws Exception {

        ItemCreationRequest itemCreationRequest = new ItemCreationRequest();
        itemCreationRequest.setItemTitle("Rice");
        ItemPriceAndSize itemPriceAndSize = new ItemPriceAndSize();
        itemPriceAndSize.setPrice(BigDecimal.valueOf(50));
        itemPriceAndSize.setSize("700ml");
        ItemPriceAndSize itemPriceAndSize1 = new ItemPriceAndSize();
        itemPriceAndSize1.setSize("500ml");
        itemPriceAndSize1.setPrice(BigDecimal.valueOf(100));

        List<ItemPriceAndSize> listOfItemPriceAndSize = new ArrayList<>();
        listOfItemPriceAndSize.add(itemPriceAndSize);
        listOfItemPriceAndSize.add(itemPriceAndSize1);

        itemCreationRequest.setItemPriceAndSize(listOfItemPriceAndSize);

        itemCreationRequest.setItemCategory("SWALLOW");
        itemCreationRequest.setPublishingType("ONLINE");

        List<String> listOfIngredients = List.of(new String[]{"Pepper", "Ginger", "Salt",});
        itemCreationRequest.setIngredients(listOfIngredients);
        itemCreationRequest.setAllergy("Cold");


        System.out.println("I'm the list of available units" + itemCreationRequest.getListOfUnitsAvailable().get(1));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/itemCreation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));


        OrderRequest swallowOrderRequest = new OrderRequest();
        OrderItemRequest swallowOrderItemRequest = new OrderItemRequest();
        swallowOrderItemRequest.setQuantity(10);
        swallowOrderItemRequest.setSubTotal(BigDecimal.valueOf(1000));
        swallowOrderItemRequest.setItemId(1L);

        swallowOrderRequest.setUnitName("LONDON");
        swallowOrderRequest.setOrderItemRequestList(List.of(swallowOrderItemRequest));
        swallowOrderRequest.setTotal(1500L);
        swallowOrderRequest.setOrderFrom("ONLINE");
        swallowOrderRequest.setPaymentType("CASH");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(swallowOrderRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));




        PaginationRequest paginationRequest = new PaginationRequest();
        paginationRequest.setPageSize(10);
        paginationRequest.setPageNumber(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/allItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paginationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));



        Item item = new Item();
        item.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item.setItemTitle("Coke");
        item.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        item.setItemPriceAndSize(listOfItemPriceAndSize);
        var savedItem = itemRepository.save(item);

        List<ItemPriceAndSize> listOfItemPriceAndSize1 = new ArrayList<>();

        ItemPriceAndSize itemPriceAndSize2 = new ItemPriceAndSize();
        itemPriceAndSize2.setSize("500ml");
        itemPriceAndSize2.setPrice(BigDecimal.valueOf(100));
        listOfItemPriceAndSize1.add(itemPriceAndSize2);

        Item item1 = new Item();
        item1.setItemTitle("Fanta");
        item1.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item1.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        item1.setItemPriceAndSize(listOfItemPriceAndSize1);
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
        orderRequest.setOrderFrom("ONLINE");
        orderRequest.setTotal(10000L);
        orderRequest.setPaymentType("CREDIT_DEBIT_CARD");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());



        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/makeOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Long order_Id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/orderCompletion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("orderId", String.valueOf(order_Id)))
                .andExpect(MockMvcResultMatchers.status().isOk());


        Long order_Id2 = 2L;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/Orders/orderCompletion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("orderId", String.valueOf(order_Id2)))
                .andExpect(MockMvcResultMatchers.status().isOk());



        Orders orders = new Orders();
        orders.setUnitName(UnitName.valueOf("LONDON"));
        orders.setOrderedTime(LocalDateTime.parse("2023-08-10 10:00 AM", DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a", Locale.ENGLISH)));
        orders.setOrderStatus(OrderStatus.COMPLETED);
        orders.setTotal(BigDecimal.valueOf(500));
        orders.setOrderFrom(OrderFrom.ONLINE);
        orders.setPaymentType(PaymentType.valueOf("CREDIT_DEBIT_CARD"));
        orderRepository.save(orders);



        UnitName unitName = UnitName.LONDON;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/unitDashboardDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("unitName", String.valueOf(unitName)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}