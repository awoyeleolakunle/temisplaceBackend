package com.dansaki.com.temisplacebackend.services.item.itemHomePageDisplay;

import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.request.AUnitItemsUnderItemCategoryRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;


public interface ItemDisplayService {
    ApiResponse enableHomepageDisplayFeature(Long itemId);

    ApiResponse disAbleHomePageDisplayFeature(Long itemId);

    List<Item> findAUnitAllHomePagedDisplayEnabledItemUnderItemCategory(String itemCategory, String unitName);

}
