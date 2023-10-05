package com.dansaki.com.temisplacebackend.services.item.itemService;

import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.AUnitItemsUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.dtos.request.AllItemsUnderAnItemCategoryRequest;
import com.dansaki.com.temisplacebackend.dtos.request.AvailableUnitItemUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    Optional<Item> findById(Long id);

    List<Item> findAllPaginatedItems(PaginationRequest paginationRequest);
    List<Item> findAllItems();

    List<Item> findAUnitAvailableItemsUnderItemCategory(AvailableUnitItemUnderItemCategoryRequest availableUnitItemUnderItemCategoryRequest );


    List<Item> findAllItemsUnderAnItemCategory(AllItemsUnderAnItemCategoryRequest allItemsUnderAnItemCategoryRequest);

    List<String> returnNamesOfAllItemCategory();

    List<Item> findAUnitAllItemsUnderItemCategory(AUnitItemsUnderItemCategoryRequest aUnitItemsUnderItemCategoryRequest);

}
