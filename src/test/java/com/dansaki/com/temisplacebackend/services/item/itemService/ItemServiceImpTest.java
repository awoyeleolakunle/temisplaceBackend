package com.dansaki.com.temisplacebackend.services.item.itemService;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.data.repositories.ItemRepository;
import com.dansaki.com.temisplacebackend.dtos.request.*;

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
class ItemServiceImpTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void testThatAllItemsCanBeFetched() throws Exception {

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


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/itemCreation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));


        PaginationRequest paginationRequest = new PaginationRequest();
        paginationRequest.setPageSize(10);
        paginationRequest.setPageNumber(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/allItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paginationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void findAUnitAvailableItemsUnderItemCategory() throws Exception {


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


        Item item = new Item();
        item.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item.setItemTitle("Coke");
        item.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        item.setItemPriceAndSize(listOfItemPriceAndSize);
        var savedItem = itemRepository.save(item);
        Item item1 = new Item();
        item1.setItemTitle("Fanta");
        item1.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item1.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        // item1.setItemPriceAndSize(listOfItemPriceAndSize);
        var secondSavedItem = itemRepository.save(item1);


        AvailableUnitItemUnderItemCategoryRequest availableUnitItemUnderItemCategoryRequest = new AvailableUnitItemUnderItemCategoryRequest();
        availableUnitItemUnderItemCategoryRequest.setUnitName("LONDON");
        availableUnitItemUnderItemCategoryRequest.setItemCategory("DRINKS");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/availableUnitItemsUnderItemCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(availableUnitItemUnderItemCategoryRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void findAllItemsUnderAnItemCategory() throws Exception {


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


        Item item = new Item();
        item.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item.setItemTitle("Coke");
        item.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        item.setItemPriceAndSize(listOfItemPriceAndSize);
        var savedItem = itemRepository.save(item);
        Item item1 = new Item();
        item1.setItemTitle("Fanta");
        item1.setItemCategory(ItemCategory.valueOf("DRINKS"));
        item1.setListOfUnitsAvailable(List.of(new String[]{"SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"}));
        // item1.setItemPriceAndSize(listOfItemPriceAndSize);
        var secondSavedItem = itemRepository.save(item1);

        AllItemsUnderAnItemCategoryRequest allItemsUnderAnItemCategoryRequest = new AllItemsUnderAnItemCategoryRequest();
        allItemsUnderAnItemCategoryRequest.setItemCategory("DRINKS");
        allItemsUnderAnItemCategoryRequest.setPageSize(10);
        allItemsUnderAnItemCategoryRequest.setPageNumber(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/allItemsUnderAnItemCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(allItemsUnderAnItemCategoryRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));


    }
}