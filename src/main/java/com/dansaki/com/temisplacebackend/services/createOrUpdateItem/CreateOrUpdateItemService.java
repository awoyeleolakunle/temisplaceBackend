package com.dansaki.com.temisplacebackend.services.createOrUpdateItem;

import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface CreateOrUpdateItemService {

    ApiResponse createOrUpdateItem(ItemCreationRequest itemCreationRequest);
}
