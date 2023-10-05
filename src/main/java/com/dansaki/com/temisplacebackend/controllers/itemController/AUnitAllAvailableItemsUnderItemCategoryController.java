package com.dansaki.com.temisplacebackend.controllers.itemController;


import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.AUnitItemsUnderItemCategoryRequest;
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
public class AUnitAllAvailableItemsUnderItemCategoryController {

    private final ItemService itemService;


    @PostMapping("AUnitAllItemsUnderItemCategory")

    public ResponseEntity<List<Item>> findUnitAllItemsUnderItemCategory(@RequestBody AUnitItemsUnderItemCategoryRequest aUnitItemsUnderItemCategoryRequest){
        return new ResponseEntity<>(itemService.findAUnitAllItemsUnderItemCategory(aUnitItemsUnderItemCategoryRequest), HttpStatus.OK);
    }
}
