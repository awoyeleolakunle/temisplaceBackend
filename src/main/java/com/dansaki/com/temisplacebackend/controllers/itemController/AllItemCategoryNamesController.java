package com.dansaki.com.temisplacebackend.controllers.itemController;


import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class AllItemCategoryNamesController {
    private final ItemService itemService;

    @PostMapping("namesOfAllItemCategory")
    public ResponseEntity<List<String>> fetchNamesOfAllItemCategory(){

        return new ResponseEntity<>(itemService.returnNamesOfAllItemCategory(), HttpStatus.OK);
    }
}
