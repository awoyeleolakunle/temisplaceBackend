package com.dansaki.com.temisplacebackend.controllers.itemCategoryAndImagesController;


import com.dansaki.com.temisplacebackend.dtos.request.ItemCategoryNameAndImageRequest;
import com.dansaki.com.temisplacebackend.services.itemCategoryNameAndImages.ItemCategoryNameAndImageService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ItemCategoryNameAndImageCreationController {
    private final ItemCategoryNameAndImageService itemCategoryNameAndImageService;

    @PostMapping("itemCategoryNameAndImageCreation")
    public ResponseEntity<ApiResponse> createItemCategoryAndImageCreation(@RequestBody ItemCategoryNameAndImageRequest itemCategoryNameAndImageRequest){
        return new ResponseEntity<>(itemCategoryNameAndImageService.uploadItemCategoryNameAndImage(itemCategoryNameAndImageRequest), HttpStatus.OK);
    }
}
