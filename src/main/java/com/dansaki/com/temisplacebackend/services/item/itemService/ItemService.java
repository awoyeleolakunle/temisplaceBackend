package com.dansaki.com.temisplacebackend.services.item.itemService;

import com.dansaki.com.temisplacebackend.data.models.Item;

import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    Optional<Item> findById(Long id);

}
