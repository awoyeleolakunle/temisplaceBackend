package com.dansaki.com.temisplacebackend.services.footer;

import com.dansaki.com.temisplacebackend.dtos.request.FooterDetailsRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface FooterCreationOrUpdateService {
    ApiResponse createOrUpdateFooterDetails(FooterDetailsRequest footerDetailsRequest);
}
