package com.dansaki.com.temisplacebackend.services.item.itemCreation;

import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
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
class ItemCreationServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatAnItemCanBeCreated() throws Exception {

        ItemCreationRequest itemCreationRequest = new ItemCreationRequest();
        itemCreationRequest.setItemCategory("DRINKS");
        List<String> ingredients = List.of(new String[]{"Pepper", "Onions", "Beans"});
        itemCreationRequest.setIngredients(ingredients);
        itemCreationRequest.setPublishingType("ONLINE");
        itemCreationRequest.setItemTitle("ItemTitle");
        ItemPriceAndSize itemPriceAndSize = new ItemPriceAndSize();
        itemPriceAndSize.setSizes("750ML");
        itemPriceAndSize.setPrice(BigDecimal.valueOf(100));
        List<ItemPriceAndSize> listOfItemType = new ArrayList<>();
        listOfItemType.add(itemPriceAndSize);
        itemCreationRequest.setItemPriceAndSize(listOfItemType);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/itemCreation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}