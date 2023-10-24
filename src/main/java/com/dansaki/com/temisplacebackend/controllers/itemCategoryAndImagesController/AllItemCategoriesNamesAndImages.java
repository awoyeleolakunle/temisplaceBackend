package com.dansaki.com.temisplacebackend.controllers.itemCategoryAndImagesController;


import com.dansaki.com.temisplacebackend.data.models.ItemCategoryNameAndImage;
import com.dansaki.com.temisplacebackend.services.itemCategoryNameAndImages.ItemCategoryNameAndImageService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
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
public class AllItemCategoriesNamesAndImages {

    private final ItemCategoryNameAndImageService itemCategoryNameAndImageService;

    @PostMapping("allItemCategoryNamesAndImages")

    public ResponseEntity<List<ItemCategoryNameAndImage>> findAllItemCategoryNamesAndImages(){
        return new ResponseEntity<>(itemCategoryNameAndImageService.findAllItemCategoryNamesAndImages(), HttpStatus.OK);
    }
}
