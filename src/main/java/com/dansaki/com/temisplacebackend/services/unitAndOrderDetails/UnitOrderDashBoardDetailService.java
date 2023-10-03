package com.dansaki.com.temisplacebackend.services.unitAndOrderDetails;

import com.dansaki.com.temisplacebackend.dtos.response.UnitOrderDashBoardDetailResponse;

public interface UnitOrderDashBoardDetailService {
    UnitOrderDashBoardDetailResponse fetchTodayUnitOrderDetails(String unitName);
}
