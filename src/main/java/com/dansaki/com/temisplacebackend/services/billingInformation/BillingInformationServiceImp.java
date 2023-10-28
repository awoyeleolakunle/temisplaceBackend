package com.dansaki.com.temisplacebackend.services.billingInformation;


import com.dansaki.com.temisplacebackend.data.models.BillingInformation;
import com.dansaki.com.temisplacebackend.data.repositories.BillingInformationRepository;
import com.dansaki.com.temisplacebackend.dtos.request.BillingInformationRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillingInformationServiceImp  implements  BillingInformationService{

    private final BillingInformationRepository billingInformationRepository;
   private final ModelMapper modelMapper;
    @Override
    public BillingInformation createBillingInformation(BillingInformationRequest billingInformationRequest) {

        BillingInformation billingInformation = modelMapper.map(billingInformationRequest, BillingInformation.class);
        return billingInformationRepository.save(billingInformation);
    }
}
