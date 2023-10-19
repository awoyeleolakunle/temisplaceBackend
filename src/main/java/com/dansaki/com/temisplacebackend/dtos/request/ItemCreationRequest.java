package com.dansaki.com.temisplacebackend.dtos.request;


import com.dansaki.com.temisplacebackend.data.models.ItemPriceAndSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreationRequest {
    private String itemTitle;
    private String itemCategory;
    private String ItemImgUrl;
    private List<String> ingredients;
    private List<ItemPriceAndSize> itemPriceAndSize;
    private String publishingType;
    private String allergy;
    private List<String> listOfUnitsAvailable =
            List.of(new String[]{ "SUNDERLAND", "LONDON", "NEWCASTLE", "GLOUCESTERSHIRE", "COVENTRY"});
    private String itemSize;
}
