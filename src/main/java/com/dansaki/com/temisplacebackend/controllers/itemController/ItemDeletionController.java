package com.dansaki.com.temisplacebackend.controllers.itemController;


import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ItemDeletionController {

    private final ItemService itemService;

    @PostMapping("itemDeletionById")
    public ResponseEntity<ApiResponse> deleteItem(@RequestParam Long id){
        System.out.println("I entered deletion");
        return new ResponseEntity<>(itemService.deleteItemById(id), HttpStatus.OK);
    }
}
