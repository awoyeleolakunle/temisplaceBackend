package com.dansaki.com.temisplacebackend.services.item.itemCreation;

import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface ItemCreationService {

    ApiResponse createItem(ItemCreationRequest itemCreationRequest);
}
