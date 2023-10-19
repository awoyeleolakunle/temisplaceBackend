package com.dansaki.com.temisplacebackend.services.itemCategoryNameAndImages;


import com.dansaki.com.temisplacebackend.data.models.ItemCategoryNameAndImage;
import com.dansaki.com.temisplacebackend.data.repositories.ItemCategoryNameAndImageRepository;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCategoryNameAndImageRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemCategoryNameAndImagesServiceImp implements ItemCategoryNameAndImageService {

    private final ItemCategoryNameAndImageRepository itemCategoryNameAndImageRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse uploadItemCategoryNameAndImage(ItemCategoryNameAndImageRequest itemCategoryNameAndImageRequest) {

        Optional<ItemCategoryNameAndImage> foundItemCategoryNameAndImage = itemCategoryNameAndImageRepository.findByItemCategoryName(itemCategoryNameAndImageRequest.getItemCategoryName());
        if (foundItemCategoryNameAndImage.isEmpty()) {
            ItemCategoryNameAndImage itemCategoryNameAndImage = modelMapper.map(itemCategoryNameAndImageRequest, ItemCategoryNameAndImage.class);
            itemCategoryNameAndImageRepository.save(itemCategoryNameAndImage);
            return GenerateApiResponse.createdResponse(GenerateApiResponse.ItEM_CATEGORY_AND_IMAGE_SUCCESSFULLY_UPLOADED);
        } else {
            updateCategoryUrl(foundItemCategoryNameAndImage.get(), itemCategoryNameAndImageRequest);
            return GenerateApiResponse.UpdateStatus(GenerateApiResponse.ItEM_CATEGORY_IMAGE_SUCCESSFULLY_UPDATED);
        }

    }

    private void updateCategoryUrl(ItemCategoryNameAndImage itemCategoryNameAndImage, ItemCategoryNameAndImageRequest itemCategoryNameAndImageRequest) {

        itemCategoryNameAndImage.setItemCategoryImageUrl(itemCategoryNameAndImageRequest.getItemCategoryImageUrl());
        itemCategoryNameAndImageRepository.save(itemCategoryNameAndImage);
    }

}