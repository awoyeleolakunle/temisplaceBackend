package com.dansaki.com.temisplacebackend.services.itemPriceAndSize;


import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.data.repositories.ItemPriceAndSizeRepository;
import com.dansaki.com.temisplacebackend.dtos.request.UpdateAvailableItemSizeAndPriceInAunitRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemPriceAndSizeServiceImp implements ItemPriceAndSizeService{

    private final ItemPriceAndSizeRepository itemPriceAndSizeRepository;

    @Override
    public ItemPriceAndSize save(ItemPriceAndSize itemPriceAndSize) {
        return itemPriceAndSizeRepository.save(itemPriceAndSize);
    }

    @Override
    public ApiResponse removeItemSizeAndPriceAvailabilityFromAUnit(UpdateAvailableItemSizeAndPriceInAunitRequest updateAvailableItemSizeAndPriceInAunitRequest) {
        Optional<ItemPriceAndSize> itemPriceAndSize = itemPriceAndSizeRepository.findById(updateAvailableItemSizeAndPriceInAunitRequest.getId());
        if(itemPriceAndSize.isPresent()){
          List<String> listOfUnitsAvailable =   itemPriceAndSize.get().getListOfUnitsAvailable();
         List<String> updateAvailableUnits = listOfUnitsAvailable.stream().filter(item -> !item.equals(updateAvailableItemSizeAndPriceInAunitRequest.getUnitName())).toList();

         itemPriceAndSize.get().setListOfUnitsAvailable(new ArrayList<>(updateAvailableUnits));
         itemPriceAndSizeRepository.save(itemPriceAndSize.get());

         return GenerateApiResponse.UpdateStatus(GenerateApiResponse.ITEM_SIZE_SUCCESSFULLY_REMOVED);
        }
        else {
            return GenerateApiResponse.NoSuchItem(GenerateApiResponse.ITEM_NOT_FOUND);
        }
    }

    @Override
    public ApiResponse addItemSizeAndPriceAvailabilityToAUnit(UpdateAvailableItemSizeAndPriceInAunitRequest updateAvailableItemSizeAndPriceInAunitRequest) {

        Optional<ItemPriceAndSize> itemPriceAndSize = itemPriceAndSizeRepository.findById(updateAvailableItemSizeAndPriceInAunitRequest.getId());
        if(itemPriceAndSize.isPresent()){
            List<String> listOfUnitsAvailable =   itemPriceAndSize.get().getListOfUnitsAvailable();
            List<String> updateAvailableUnits = new ArrayList<>(listOfUnitsAvailable.stream()
                    .filter(item -> !item.equals(updateAvailableItemSizeAndPriceInAunitRequest.getUnitName())).toList());

            updateAvailableUnits.add(updateAvailableItemSizeAndPriceInAunitRequest.getUnitName());
            itemPriceAndSize.get().setListOfUnitsAvailable(new ArrayList<>(updateAvailableUnits));
            itemPriceAndSizeRepository.save(itemPriceAndSize.get());

            return GenerateApiResponse.UpdateStatus(GenerateApiResponse.ITEM_SIZE_SUCCESSFULLY_ADDED);
        }

        else {
        return GenerateApiResponse.NoSuchItem(GenerateApiResponse.ITEM_NOT_FOUND);
        }

    }

}
