package com.dansaki.com.temisplacebackend.services.verificationToken;

import com.dansaki.com.temisplacebackend.data.models.VerificationToken;
import com.dansaki.com.temisplacebackend.dtos.request.OtpConfirmationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

import java.util.Optional;

public interface VerificationTokenService {
    void save(VerificationToken verificationToken);
    Optional<VerificationToken> getVerificationToken(String token);
    ApiResponse confirmToken(OtpConfirmationRequest otpConfirmationRequest);
    String generateVerificationToken(int length);
}
