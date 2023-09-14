package com.dansaki.com.temisplacebackend.services.item.itemCreation;


import com.dansaki.com.temisplacebackend.data.models.Item;

import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.services.itemPriceAndSize.ItemPriceAndSizeService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemCreationServiceImp implements ItemCreationService {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    private final ItemPriceAndSizeService itemPriceAndSizeService;

    @Override
    public ApiResponse createItem(ItemCreationRequest itemCreationRequest) {

        Item item = modelMapper.map(itemCreationRequest, Item.class);
       var savedItem = itemService.save(item);
        System.out.println(savedItem.getIngredients().size());
        System.out.println(savedItem.getIngredients().get(1));
        return GenerateApiResponse.createdResponse(GenerateApiResponse.ITEM_CREATED_SUCCESSFULLY);
    }
}
