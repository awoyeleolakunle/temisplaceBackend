package com.dansaki.com.temisplacebackend.services.footer;


import com.dansaki.com.temisplacebackend.data.models.Footer;
import com.dansaki.com.temisplacebackend.data.repositories.FooterRepository;
import com.dansaki.com.temisplacebackend.dtos.request.FooterDetailsRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FooterCreationOrUpdateServiceImp implements FooterCreationOrUpdateService{

    private final ModelMapper modelMapper;
    private final FooterRepository footerRepository;

    @Override
    public ApiResponse createOrUpdateFooterDetails(FooterDetailsRequest footerDetailsRequest) {

        Footer foundFooter = footerRepository.findById(1L).orElse(null);
        if(foundFooter==null) {
            Footer footer = modelMapper.map(footerDetailsRequest, Footer.class);
            footerRepository.save(footer);
            return GenerateApiResponse.createdResponse(GenerateApiResponse.FOOTER_CREATED_SUCCESSFULLY);
        }
        else{
            updateFooter(foundFooter, footerDetailsRequest);
            return GenerateApiResponse.updateSuccessful(GenerateApiResponse.FOOTER_UPDATED_SUCCESSFULLY);
        }
    }

    private void updateFooter(Footer foundFooter, FooterDetailsRequest footerDetailsRequest) {
        if(footerDetailsRequest.getFooterImgUrl()!=null){
        foundFooter.setFooterImgUrl(footerDetailsRequest.getFooterImgUrl());
        }
        if(footerDetailsRequest.getSlogan()!=null){
            foundFooter.setSlogan(footerDetailsRequest.getSlogan());
        }
        if(footerDetailsRequest.getTelephone()!=null){
            foundFooter.setTelephone(footerDetailsRequest.getTelephone());
        }
        if(footerDetailsRequest.getAddress()!=null){
            foundFooter.setAddress(foundFooter.getAddress());
        }
        if(footerDetailsRequest.getFacebook()!=null){
            foundFooter.setFacebook(footerDetailsRequest.getFacebook());
        }
        if(footerDetailsRequest.getInstagram()!=null){
            foundFooter.setLinkedin(footerDetailsRequest.getLinkedin());
        }
        if(footerDetailsRequest.getCity()!=null){
            foundFooter.setCity(footerDetailsRequest.getCity());
        }
        if(footerDetailsRequest.getTwitter()!=null){
            foundFooter.setTwitter(foundFooter.getTwitter());
        }
        if(footerDetailsRequest.getOverview()!=null){
            foundFooter.setOverview(foundFooter.getOverview());
        }
        if(footerDetailsRequest.getPostCode()!=null){
            foundFooter.setPostCode(footerDetailsRequest.getPostCode());
        }
        if(footerDetailsRequest.getLinkedin()!=null){
            foundFooter.setLinkedin(footerDetailsRequest.getLinkedin());
        }

        footerRepository.save(foundFooter);

    }
}
