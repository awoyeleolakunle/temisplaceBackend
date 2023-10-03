package com.dansaki.com.temisplacebackend.controllers.itemController;


import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.AvailableUnitItemUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AUnitAvailableItemsUnderItemCategoryController {

    private final ItemService itemService;

    @PostMapping("availableUnitItemsUnderItemCategory")

    public ResponseEntity<List<Item>> fetchUnitItemsUnderItemCategory( @RequestBody AvailableUnitItemUnderItemCategoryRequest availableUnitItemUnderItemCategoryRequest){
        return new ResponseEntity<>(itemService.findAUnitAvailableItemsUnderItemCategory(availableUnitItemUnderItemCategoryRequest), HttpStatus.OK);
    }
}
