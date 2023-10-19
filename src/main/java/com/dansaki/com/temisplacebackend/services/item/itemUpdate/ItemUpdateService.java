package com.dansaki.com.temisplacebackend.services.item.itemUpdate;


import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface ItemUpdateService {
    ApiResponse updateItem(ItemCreationRequest itemCreationRequest);
}
