package com.dansaki.com.temisplacebackend.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoryNameAndImageRequest {
    private String itemCategoryName;
    private String itemCategoryImageUrl;
}
