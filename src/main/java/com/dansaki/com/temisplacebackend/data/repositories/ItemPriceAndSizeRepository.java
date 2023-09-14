package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceAndSizeRepository extends JpaRepository<ItemPriceAndSize, Long> {
}
