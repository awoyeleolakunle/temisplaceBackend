package com.dansaki.com.temisplacebackend.services.item.unitAndItem.unitItemAddition;

import com.dansaki.com.temisplacebackend.dtos.request.UnitItemAvailabilityRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface UnitItemAvailabilityAdditionService {
    ApiResponse addItemAvailabilityToUnit(UnitItemAvailabilityRequest unitItemAvailabilityRemovalRequest);
}
