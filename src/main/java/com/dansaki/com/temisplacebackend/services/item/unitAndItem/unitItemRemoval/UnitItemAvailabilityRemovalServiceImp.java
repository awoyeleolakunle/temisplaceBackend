package com.dansaki.com.temisplacebackend.services.item.unitAndItem.unitItemRemoval;

import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.UnitItemAvailabilityRequest;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UnitItemAvailabilityRemovalServiceImp implements UnitItemAvailabilityRemovalService {

    private final ItemService itemService;
    @Override
    public ApiResponse removeItemFromUnit(UnitItemAvailabilityRequest unitItemAvailabilityRemovalRequest) {

       Item item = findItemById(unitItemAvailabilityRemovalRequest.getItemId());
       removeItemAvailabilityFromUnit(item, unitItemAvailabilityRemovalRequest.getUnitName().toUpperCase());

        return GenerateApiResponse.UpdateStatus(GenerateApiResponse.ITEM_SUCCESSFULLY_REMOVED);
    }


    private Item findItemById(Long itemId){
        return itemService.findById(itemId).orElse(null);
    }

    private void removeItemAvailabilityFromUnit(Item item, String unitName){
        List<String> listOfAvailableUnits =  item.getListOfUnitsAvailable();
       List<String> filteredAvailableUnitName = listOfAvailableUnits.stream().filter(name -> !name.equals(unitName)).toList();
        item.setListOfUnitsAvailable(new ArrayList<>(filteredAvailableUnitName));
      var savedItem =  itemService.save(item);
      savedItem.getListOfUnitsAvailable().forEach(System.out::println);
    }
}
