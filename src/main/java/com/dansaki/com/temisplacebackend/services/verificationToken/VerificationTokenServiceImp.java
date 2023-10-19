package com.dansaki.com.temisplacebackend.services.verificationToken;

import com.dansaki.com.temisplacebackend.data.models.Roles;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.data.models.VerificationToken;
import com.dansaki.com.temisplacebackend.data.repositories.VerificationTokenRepository;
import com.dansaki.com.temisplacebackend.dtos.request.OtpConfirmationRequest;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImp implements VerificationTokenService{

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserService userService;
    @Override
    public void save(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public Optional<VerificationToken> getVerificationToken(String token) {
        return verificationTokenRepository.findByVerificationToken(token);
    }

    @Override
    public ApiResponse confirmToken(OtpConfirmationRequest otpConfirmationRequest) {
        if (isValidToken(otpConfirmationRequest.getToken(), otpConfirmationRequest.getEmailAddress())){
            User foundUser = userService.findUserByEmailAddress(otpConfirmationRequest.getEmailAddress());
            Set<Roles> userRole  = foundUser.getRoles();
            return ApiResponse.builder()
                    .data(userRole)
                    .isSuccessful(true)
                    .statusCode(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        return ApiResponse.builder()
                .data(GenerateApiResponse.INVALID_CREDENTIALS)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    @Override
    public String generateVerificationToken(int length) {
        byte [] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }

    private boolean isValidToken(String token, String emailAddress) {

        Optional<VerificationToken> foundToken = getVerificationToken(token);
        if(foundToken.isPresent()){
            String email = foundToken.get().getEmailAddress();
            System.out.println("i'm inside");
            return email.equals(emailAddress);

        }
        return false;
    }
}
