package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AUnitItemsUnderItemCategoryRequest {

    private String unitName;
    private String itemCategory;
}
