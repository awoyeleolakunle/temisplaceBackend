package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.models.ItemCategoryNameAndImage;
import com.dansaki.com.temisplacebackend.dtos.request.ItemCategoryNameAndImageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCategoryNameAndImageRepository extends JpaRepository<ItemCategoryNameAndImage, Long> {

    Optional<ItemCategoryNameAndImage> findByItemCategoryName(String itemCategoryName);
}
