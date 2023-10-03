package com.dansaki.com.temisplacebackend.services.item.unitAndItem.unitItemAddition;

import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.UnitItemAvailabilityRequest;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class UnitItemAvailabilityAdditionServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addItemAvailabilityToUnit() throws Exception {

        ItemCreationRequest itemCreationRequest = new ItemCreationRequest();
        itemCreationRequest.setItemCategory("DRINKS");
        List<String> ingredients = List.of(new String[]{"Pepper", "Onions", "Beans"});
        itemCreationRequest.setIngredients(ingredients);
        itemCreationRequest.setPublishingType("ONLINE");
        itemCreationRequest.setItemTitle("ItemTitle");
        ItemPriceAndSize itemPriceAndSize = new ItemPriceAndSize();
        itemPriceAndSize.setSize("750ML");
        itemPriceAndSize.setPrice(BigDecimal.valueOf(100));
        List<ItemPriceAndSize> listOfItemType = new ArrayList<>();
        listOfItemType.add(itemPriceAndSize);
        itemCreationRequest.setItemPriceAndSize(listOfItemType);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/itemCreation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());


        ItemCreationRequest itemCreationRequest1 = new ItemCreationRequest();
        itemCreationRequest1.setItemCategory("SWALLOW");
        List<String> ingredients1 = List.of(new String[]{"Ginger", "Garlic", "Salt"});
        itemCreationRequest1.setIngredients(ingredients1);
        itemCreationRequest1.setPublishingType("ONLINE");
        itemCreationRequest1.setItemTitle("swallowTitle");
        ItemPriceAndSize itemPriceAndSize1 = new ItemPriceAndSize();
        itemPriceAndSize1.setSize("500ML");
        itemPriceAndSize1.setPrice(BigDecimal.valueOf(200));
        List<ItemPriceAndSize> listOfItemType1 = new ArrayList<>();
        listOfItemType1.add(itemPriceAndSize1);
        itemCreationRequest1.setItemPriceAndSize(listOfItemType1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/itemCreation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreationRequest1)))
                .andExpect(MockMvcResultMatchers.status().isOk());


        UnitItemAvailabilityRequest unitItemAvailabilityRemovalRequest = new UnitItemAvailabilityRequest();
        unitItemAvailabilityRemovalRequest.setUnitName("London");
        unitItemAvailabilityRemovalRequest.setItemId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post( "/api/v1/temisplace/unitItemAvailabilityRemoval")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(unitItemAvailabilityRemovalRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));


        UnitItemAvailabilityRequest unitItemAvailabilityRequest = new UnitItemAvailabilityRequest();
        unitItemAvailabilityRequest.setUnitName("LONDON");
        unitItemAvailabilityRequest.setItemId(1L);


        mockMvc.perform(MockMvcRequestBuilders.post( "/api/v1/temisplace/unitItemAvailabilityAddition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(unitItemAvailabilityRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}