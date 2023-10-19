package com.dansaki.com.temisplacebackend.services.otp;

import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface OtpService {
    ApiResponse sendOtp(String emailAddress);
}
