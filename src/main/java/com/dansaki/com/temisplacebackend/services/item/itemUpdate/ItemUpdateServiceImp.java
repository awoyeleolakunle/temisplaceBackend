package com.dansaki.com.temisplacebackend.services.item.itemUpdate;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.services.item.itemCreation.ItemCreationService;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemUpdateServiceImp implements ItemUpdateService{

    private   final ItemService itemService;
    private final ItemCreationService itemCreationService;

    @Override
    public ApiResponse updateItem(ItemCreationRequest itemCreationRequest) {
        Optional<Item> foundItem = itemService.findByItemTitle(itemCreationRequest.getItemTitle());
        if(foundItem.isEmpty()) {return GenerateApiResponse.NoSuchItem(GenerateApiResponse.ITEM_NOT_FOUND);}
        updateFoundItem(foundItem.get(), itemCreationRequest);
        return GenerateApiResponse.updateSuccessful(GenerateApiResponse.ITEM_UPDATED_SUCCESSFULLY);
    }

    private void updateFoundItem(Item foundItem, ItemCreationRequest itemCreationRequest) {

        if(itemCreationRequest.getItemCategory()!=null){
            foundItem.setItemCategory(ItemCategory.valueOf(itemCreationRequest.getItemCategory()));
        }
        if(itemCreationRequest.getItemSize()!=null){
            foundItem.setItemSize(itemCreationRequest.getItemSize());
        }
        if(itemCreationRequest.getItemPriceAndSize()!=null){
            foundItem.setItemPriceAndSize(new ArrayList<>(itemCreationRequest.getItemPriceAndSize()));
        }
        if(itemCreationRequest.getItemImgUrl()!=null){
            foundItem.setItemImgUrl(itemCreationRequest.getItemImgUrl());
        }
        if(itemCreationRequest.getIngredients()!=null){
            foundItem.setIngredients(new ArrayList<>(itemCreationRequest.getIngredients()));
        }

        if(itemCreationRequest.getAllergy()!=null){
            foundItem.setAllergy(itemCreationRequest.getAllergy());
        }

        itemService.save(foundItem);
    }
}
