package com.dansaki.com.temisplacebackend.services.billingInformation;

import com.dansaki.com.temisplacebackend.data.models.BillingInformation;
import com.dansaki.com.temisplacebackend.dtos.request.BillingInformationRequest;

public interface BillingInformationService {
    BillingInformation createBillingInformation(BillingInformationRequest billingInformationRequest);
}
