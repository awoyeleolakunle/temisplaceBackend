package com.dansaki.com.temisplacebackend.services.orders.manageActiveOrderStatusAndOrderStatus;

import com.dansaki.com.temisplacebackend.dtos.request.ActiveOrderStatusAndOrderStatusRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;


public interface ActiveOrderStatusAndOrderStatusManagementService {
    ApiResponse manageActiveOrderStatusAndOrderStatus(ActiveOrderStatusAndOrderStatusRequest activeOrderStatusAndOrderStatusRequest);
}
