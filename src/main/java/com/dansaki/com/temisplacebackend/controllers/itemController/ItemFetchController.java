package com.dansaki.com.temisplacebackend.controllers.itemController;


import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ItemFetchController {

    private final ItemService itemService;


    @PostMapping("itemFetchById")
    public ResponseEntity<Item> findItemById(@RequestParam Long itemId){

        return new ResponseEntity<>(itemService.findByItemId(itemId), HttpStatus.OK);
    }
}
