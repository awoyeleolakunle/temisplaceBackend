package com.dansaki.com.temisplacebackend.controllers.itemController;

import com.dansaki.com.temisplacebackend.dtos.request.ItemCreationRequest;
import com.dansaki.com.temisplacebackend.services.item.itemCreation.ItemCreationService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ItemCreationController {
    private final ItemCreationService itemCreationService;

    @PostMapping("itemCreation")
    public ResponseEntity<ApiResponse> createItem(@RequestBody ItemCreationRequest itemCreationRequest){
        return new ResponseEntity<>(itemCreationService.createItem(itemCreationRequest), HttpStatus.OK);
    }
}
