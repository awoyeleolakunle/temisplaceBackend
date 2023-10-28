package com.dansaki.com.temisplacebackend.controllers.itemSizeAndPriceControllers;


import com.dansaki.com.temisplacebackend.dtos.request.UpdateAvailableItemSizeAndPriceInAunitRequest;
import com.dansaki.com.temisplacebackend.services.itemPriceAndSize.ItemPriceAndSizeService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class ItemSizeAndPriceRemovalToUnitController {

    private final ItemPriceAndSizeService itemPriceAndSizeService;
    @PostMapping("itemSizeAndPriceAvailabilityRemovalFromAUnit")
    public ResponseEntity<ApiResponse> removeItemSizeAndPriceAvailabilityFromAUnit(@RequestBody UpdateAvailableItemSizeAndPriceInAunitRequest updateAvailableItemSizeAndPriceInAunitRequest){
        return new ResponseEntity<>(itemPriceAndSizeService.removeItemSizeAndPriceAvailabilityFromAUnit(updateAvailableItemSizeAndPriceInAunitRequest), HttpStatus.OK);
    }
}
