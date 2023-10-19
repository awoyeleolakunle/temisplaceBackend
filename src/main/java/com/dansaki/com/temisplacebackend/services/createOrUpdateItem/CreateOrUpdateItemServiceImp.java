package com.dansaki.com.temisplacebackend.services.createOrUpdateItem;

import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.services.item.itemCreation.ItemCreationService;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.services.item.itemUpdate.ItemUpdateService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOrUpdateItemServiceImp implements CreateOrUpdateItemService {
    private final ItemService itemService;
    private final ItemUpdateService itemUpdateService;
    private final ItemCreationService itemCreationService;
    @Override
    public ApiResponse createOrUpdateItem(ItemCreationRequest itemCreationRequest) {

       boolean isNotCreated = itemService.findByItemTitle(itemCreationRequest.getItemTitle()).isEmpty();
       if(isNotCreated){
           itemCreationService.createItem(itemCreationRequest);
       }
       else {
           itemUpdateService.updateItem(itemCreationRequest);
       }
       return GenerateApiResponse.incorrectDetails(GenerateApiResponse.INCORRECT_DETAILS);
    }
}
