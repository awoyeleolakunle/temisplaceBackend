package com.dansaki.com.temisplacebackend.services.item.unitAndItem.unitItemRemoval;

import com.dansaki.com.temisplacebackend.dtos.request.UnitItemAvailabilityRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface UnitItemAvailabilityRemovalService {
    ApiResponse removeItemFromUnit(UnitItemAvailabilityRequest unitItemAvailabilityRemovalRequest);
}
