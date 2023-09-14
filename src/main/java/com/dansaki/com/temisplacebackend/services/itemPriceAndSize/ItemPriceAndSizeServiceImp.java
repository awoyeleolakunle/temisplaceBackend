package com.dansaki.com.temisplacebackend.services.itemPriceAndSize;


import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import com.dansaki.com.temisplacebackend.data.repositories.ItemPriceAndSizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemPriceAndSizeServiceImp implements ItemPriceAndSizeService{

    private final ItemPriceAndSizeRepository itemPriceAndSizeRepository;

    @Override
    public ItemPriceAndSize save(ItemPriceAndSize itemPriceAndSize) {
        return itemPriceAndSizeRepository.save(itemPriceAndSize);
    }
}
