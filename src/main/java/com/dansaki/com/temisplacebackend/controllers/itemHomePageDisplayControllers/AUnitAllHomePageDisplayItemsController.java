package com.dansaki.com.temisplacebackend.controllers.itemHomePageDisplayControllers;


import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.services.item.itemHomePageDisplay.ItemDisplayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping ("api/v1/temisplace/sse/")
 @CrossOrigin (origins = "*")
@AllArgsConstructor
public class AUnitAllHomePageDisplayItemsController {

 private final ItemDisplayService itemDisplayService;

@GetMapping("aUnitAllHomePageDisplayItems")
 public ResponseEntity<List<Item>> returnAunitAllHomePageDisplayedEnabledItem(@RequestParam String itemCategory , @RequestParam String unitName ){

  return new ResponseEntity<>(itemDisplayService.findAUnitAllHomePagedDisplayEnabledItemUnderItemCategory(itemCategory, unitName), HttpStatus.OK);
  
 }

}
