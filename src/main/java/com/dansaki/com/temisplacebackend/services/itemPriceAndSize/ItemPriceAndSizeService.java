package com.dansaki.com.temisplacebackend.services.itemPriceAndSize;

import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.dtos.request.UpdateAvailableItemSizeAndPriceInAunitRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface ItemPriceAndSizeService {

    ItemPriceAndSize save(ItemPriceAndSize itemPriceAndSize);

    ApiResponse removeItemSizeAndPriceAvailabilityFromAUnit(UpdateAvailableItemSizeAndPriceInAunitRequest updateAvailableItemSizeAndPriceInAunitRequest);

    ApiResponse addItemSizeAndPriceAvailabilityToAUnit(UpdateAvailableItemSizeAndPriceInAunitRequest updateAvailableItemSizeAndPriceInAunitRequest);
}
