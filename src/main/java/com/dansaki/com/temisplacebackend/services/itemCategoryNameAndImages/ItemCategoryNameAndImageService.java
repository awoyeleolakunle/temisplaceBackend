package com.dansaki.com.temisplacebackend.services.itemCategoryNameAndImages;

import com.dansaki.com.temisplacebackend.dtos.request.ItemCategoryNameAndImageRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface ItemCategoryNameAndImageService {
    ApiResponse uploadItemCategoryNameAndImage(ItemCategoryNameAndImageRequest itemCategoryNameAndImageRequest );
}
