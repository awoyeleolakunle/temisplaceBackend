package com.dansaki.com.temisplacebackend.services.item.itemHomePageDisplay;


import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.repositories.ItemRepository;
import com.dansaki.com.temisplacebackend.dtos.request.AUnitItemsUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemDisplayServiceImp implements ItemDisplayService {

    private final ItemRepository itemRepository;
    @Override
    public ApiResponse enableHomepageDisplayFeature(Long itemId) {
  Optional<Item> item = itemRepository.findById(itemId);
  if(item.isPresent()){
      item.get().setIsDisplay(Boolean.TRUE);
      itemRepository.save(item.get());
      return GenerateApiResponse.UpdateStatus(GenerateApiResponse.HOME_PAGE_DISPLAY_ACTIVATED);
  }
      else{
          return GenerateApiResponse.NoSuchItem(GenerateApiResponse.ITEM_NOT_FOUND);
  }
    }

    @Override
    public ApiResponse disAbleHomePageDisplayFeature(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()){
            item.get().setIsDisplay(Boolean.FALSE);
            itemRepository.save(item.get());
            return GenerateApiResponse.UpdateStatus(GenerateApiResponse.HOME_PAGE_DISPLAY_DEACTIVATED);
        }
        else{
            return GenerateApiResponse.NoSuchItem(GenerateApiResponse.ITEM_NOT_FOUND);
        }
    }

    @Override
    public List<Item> findAUnitAllHomePagedDisplayEnabledItemUnderItemCategory(String itemCategory, String unitName) {

        List<Item> listOfEnabledHomePageDisplayItems = itemRepository
                .findAllByItemCategoryAndIsDisplay(
                        ItemCategory.valueOf(itemCategory.toUpperCase()),
                        Boolean.TRUE
                        );

        if (listOfEnabledHomePageDisplayItems.isEmpty()) {
            return listOfEnabledHomePageDisplayItems;
        }

        return listOfEnabledHomePageDisplayItems.stream().filter(item -> item.getListOfUnitsAvailable().contains(unitName.toUpperCase())).toList();
       }

//        if(listOfEnabledHomePageDisplayItems.isEmpty()){
//            return itemFlux;
//        }
//
//
//        for (Item item : listOfEnabledHomePageDisplayItems) {
//            itemFlux = itemFlux.concatWith(Flux.just(item));
//        }
//
//        return itemFlux
//                .doOnCancel(() -> System.out.println("SSE Stream closed"));
}
