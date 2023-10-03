package com.dansaki.com.temisplacebackend.controllers.itemController;

import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
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
public class AllItemController {

    private final ItemService itemService;

    @PostMapping("allItems")

    public ResponseEntity<List<Item>> fetchAllItems(@RequestBody PaginationRequest paginationRequest){
        return new ResponseEntity<>(itemService.findAllPaginatedItems(paginationRequest), HttpStatus.OK);
    }
}
