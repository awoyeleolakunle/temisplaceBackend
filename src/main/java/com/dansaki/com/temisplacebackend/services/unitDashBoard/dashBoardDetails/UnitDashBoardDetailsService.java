package com.dansaki.com.temisplacebackend.services.unitDashBoard.dashBoardDetails;

import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.dtos.response.ItemCategoryInfoForUnitDashBoard;

import java.util.List;

public interface UnitDashBoardDetailsService {
    List<ItemCategoryInfoForUnitDashBoard> fetchItemsDetailList(String unitName);
}
