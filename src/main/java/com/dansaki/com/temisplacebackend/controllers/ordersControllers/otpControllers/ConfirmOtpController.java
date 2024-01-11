package com.dansaki.com.temisplacebackend.controllers.ordersControllers.otpControllers;


import com.dansaki.com.temisplacebackend.data.models.VerificationToken;
import com.dansaki.com.temisplacebackend.dtos.request.OtpConfirmationRequest;
import com.dansaki.com.temisplacebackend.services.verificationToken.VerificationTokenService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/temisplace/")
 @CrossOrigin(origins = "*")
@AllArgsConstructor
public class ConfirmOtpController {
    private final VerificationTokenService verificationTokenService;

    @PostMapping("otpConfirmation")
    public ResponseEntity<ApiResponse> confirmOtp(@RequestBody OtpConfirmationRequest otpConfirmationRequest){
        return new ResponseEntity<>(verificationTokenService.confirmToken(otpConfirmationRequest), HttpStatus.OK);
    }
}
