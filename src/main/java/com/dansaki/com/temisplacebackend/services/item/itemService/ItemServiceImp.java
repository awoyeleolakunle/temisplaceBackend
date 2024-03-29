package com.dansaki.com.temisplacebackend.services.item.itemService;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.repositories.ItemRepository;
import com.dansaki.com.temisplacebackend.dtos.request.AUnitItemsUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.dtos.request.AllItemsUnderAnItemCategoryRequest;
import com.dansaki.com.temisplacebackend.dtos.request.AvailableUnitItemUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemServiceImp implements ItemService{

    private final ItemRepository  itemRepository;
    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item findByItemId(Long id) {
        var item = itemRepository.findById(id);
        if(item.isPresent()){
        return item.get();
        }
        else {
            return null;
        }
    }

    @Override
    public List<Item> findAllPaginatedItems(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        Page<Item> pages = itemRepository.findAll(pageable);
        System.out.println("i'm the size " + pages.getContent().size());
        System.out.println("i'm the first item " + pages.getContent().get(0).getIngredients());
        System.out.println("I'm the prices and sizes " + pages.getContent().get(0).getItemPriceAndSize());
        return pages.getContent();

    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAUnitAvailableItemsUnderItemCategory(AvailableUnitItemUnderItemCategoryRequest availableUnitItemUnderItemCategoryRequest) {
       List<Item> listOfItems =   itemRepository.findAllByItemCategory(ItemCategory.valueOf(availableUnitItemUnderItemCategoryRequest.getItemCategory()));
      if(listOfItems.isEmpty()){ return listOfItems;}

      List<Item> filteredList = listOfItems.stream()
                .filter(item -> item.getListOfUnitsAvailable().contains(availableUnitItemUnderItemCategoryRequest.getUnitName().toUpperCase())).toList();

     int pageSize = availableUnitItemUnderItemCategoryRequest.getPageSize();
     int pageNumber = availableUnitItemUnderItemCategoryRequest.getPageNumber();

     int totalItems = filteredList.size();
     int startIndex = (pageNumber - 1) * pageSize;
     int endIndex = Math.min(startIndex + pageSize, totalItems);

        System.out.println("i'm the endIndex " +endIndex);
         return filteredList.subList(startIndex, endIndex);
    }

    @Override
    public List<Item> findAllItemsUnderAnItemCategory(AllItemsUnderAnItemCategoryRequest allItemsUnderAnItemCategoryRequest) {
        System.out.println("I'm the item category " +  allItemsUnderAnItemCategoryRequest.getItemCategory());
        List<Item> listOfItemsUnderAnItemCategory = itemRepository.findAllByItemCategory(ItemCategory.valueOf(allItemsUnderAnItemCategoryRequest.getItemCategory().toUpperCase()));


        listOfItemsUnderAnItemCategory.forEach(System.out::println );

        int totalListSize = listOfItemsUnderAnItemCategory.size();

        System.out.println("I'm the total size in the itemCategory " + totalListSize);

        int pageNumber = allItemsUnderAnItemCategoryRequest.getPageNumber();
        int pageSize = allItemsUnderAnItemCategoryRequest.getPageSize();

        int startIndex = (pageNumber - 1) * pageSize;
        int lastIndex = Math.min(startIndex + pageSize, totalListSize);

        return listOfItemsUnderAnItemCategory.subList(startIndex, lastIndex);
    }

    @Override
    public List<String> returnNamesOfAllItemCategory() {

        List<String> namesOfAllItemCategories = new ArrayList<>();
        for (ItemCategory itemCategory: ItemCategory.values()) {
            namesOfAllItemCategories.add(itemCategory.name());
        }
        return namesOfAllItemCategories;
    }

    @Override
    public List<Item> findAUnitAllItemsUnderItemCategory(AUnitItemsUnderItemCategoryRequest aUnitItemsUnderItemCategoryRequest) {

        List<Item> allItemsByItemCategory =  itemRepository.findAllByItemCategory(ItemCategory.valueOf(aUnitItemsUnderItemCategoryRequest.getItemCategory().toUpperCase()));
        if(allItemsByItemCategory.isEmpty()) { return allItemsByItemCategory;}

        return allItemsByItemCategory.stream().filter(item -> item.getListOfUnitsAvailable().contains(aUnitItemsUnderItemCategoryRequest.getUnitName().toUpperCase())).toList();
    }

    @Override
    public Optional<Item> findByItemTitle(String itemTitle) {
        return itemRepository.findByItemTitle(itemTitle);
    }

    @Override
    public ApiResponse deleteItemById(Long id) {
        itemRepository.deleteById(id);
        return GenerateApiResponse.deleteSuccessful(GenerateApiResponse.DELETED_SUCCESSFULLY);
    }
}
