package com.dansaki.com.temisplacebackend.services.itemCategoryNameAndImages;

import com.dansaki.com.temisplacebackend.data.models.ItemCategoryNameAndImage;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCategoryNameAndImageRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

import java.util.List;

public interface ItemCategoryNameAndImageService {
    ApiResponse uploadItemCategoryNameAndImage(ItemCategoryNameAndImageRequest itemCategoryNameAndImageRequest );

    List<ItemCategoryNameAndImage> findAllItemCategoryNamesAndImages();
}
