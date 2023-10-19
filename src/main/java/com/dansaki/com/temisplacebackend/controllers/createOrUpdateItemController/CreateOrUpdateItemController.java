package com.dansaki.com.temisplacebackend.controllers.createOrUpdateItemController;


import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.services.createOrUpdateItem.CreateOrUpdateItemService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class CreateOrUpdateItemController {

    private final CreateOrUpdateItemService createOrUpdateItemService;

    @PostMapping("itemCreationOrUpdate")
    public ResponseEntity<ApiResponse> createOrUpdateItem(@RequestBody ItemCreationRequest itemCreationRequest){
        return new ResponseEntity<>(createOrUpdateItemService.createOrUpdateItem(itemCreationRequest), HttpStatus.OK);
    }
}
