package com.dansaki.com.temisplacebackend.controllers.footerController;


import com.dansaki.com.temisplacebackend.dtos.request.FooterDetailsRequest;
import com.dansaki.com.temisplacebackend.services.footer.FooterCreationOrUpdateService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FooterCreationOrUpdateController {

    private final FooterCreationOrUpdateService footerCreationOrUpdateService;

    @PostMapping("footerCreationOrUpdate")
    public ResponseEntity<ApiResponse> createOrUpdateFooter(@RequestBody FooterDetailsRequest footerDetailsRequest){
        return new ResponseEntity<>(footerCreationOrUpdateService.createOrUpdateFooterDetails(footerDetailsRequest), HttpStatus.OK);
    }
}