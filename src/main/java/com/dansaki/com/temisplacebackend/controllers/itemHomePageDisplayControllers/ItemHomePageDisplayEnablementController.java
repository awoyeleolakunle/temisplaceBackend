package com.dansaki.com.temisplacebackend.controllers.itemHomePageDisplayControllers;


import com.dansaki.com.temisplacebackend.services.item.itemHomePageDisplay.ItemDisplayService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class ItemHomePageDisplayEnablementController {

    private final ItemDisplayService itemDisplayService;

    @PostMapping("homePageDisplayEnablement")
    public ResponseEntity<ApiResponse> enableItemHomePageDisplay(@RequestParam Long itemId){

        return new ResponseEntity<>(itemDisplayService.enableHomepageDisplayFeature(itemId), HttpStatus.OK);
    }
}
