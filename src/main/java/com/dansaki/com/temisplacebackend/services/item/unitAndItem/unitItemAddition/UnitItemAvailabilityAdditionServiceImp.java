package com.dansaki.com.temisplacebackend.services.item.unitAndItem.unitItemAddition;

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
public class UnitItemAvailabilityAdditionServiceImp implements UnitItemAvailabilityAdditionService{
    private final ItemService itemService;
    @Override
    public ApiResponse addItemAvailabilityToUnit(UnitItemAvailabilityRequest unitItemAvailabilityRequest) {

        Item item= findItemById(unitItemAvailabilityRequest.getItemId());
        AddToItemAvailabilityToUnit(item, unitItemAvailabilityRequest.getUnitName());

        return GenerateApiResponse.updateSuccessful(GenerateApiResponse.ITEM_SUCCESSFULLY_ADDED);
    }

    private void AddToItemAvailabilityToUnit(Item item, String unitName) {
        List<String> unitsAvailable = item.getListOfUnitsAvailable();
        List<String> filteredAvailableUnitName = new ArrayList<>(unitsAvailable.stream().filter(name -> !name.equals(unitName)).toList());
        filteredAvailableUnitName.add(unitName.toUpperCase());
        item.setListOfUnitsAvailable(new ArrayList<>(filteredAvailableUnitName));

        var savedItem = itemService.save(item);
        savedItem.getListOfUnitsAvailable().forEach(System.out::println);
    }

    private Item findItemById(Long itemId) {
        return itemService.findById(itemId).orElse(null);
    }
}
