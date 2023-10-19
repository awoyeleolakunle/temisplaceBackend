package com.dansaki.com.temisplacebackend.controllers.otpControllers;


import com.dansaki.com.temisplacebackend.services.otp.OtpService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class SendOtpController {

    private final OtpService otpService;

    @PostMapping("sendOtp")
    public ResponseEntity<ApiResponse> sendOtp(@RequestParam String emailAddress){
        return new ResponseEntity<>(otpService.sendOtp(emailAddress), HttpStatus.OK);
    }
}
