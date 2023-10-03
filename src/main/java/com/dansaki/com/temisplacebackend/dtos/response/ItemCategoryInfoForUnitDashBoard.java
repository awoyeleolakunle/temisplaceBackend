package com.dansaki.com.temisplacebackend.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemCategoryInfoForUnitDashBoard {
    private String category;
    private int numberOfItems;
    private double percentageDifferenceInMonthlySales;
}
