package com.dansaki.com.temisplacebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableUnitItemUnderItemCategoryRequest {
    private String unitName;
    private String itemCategory;
    private int pageNumber;
    private int pageSize;
}
