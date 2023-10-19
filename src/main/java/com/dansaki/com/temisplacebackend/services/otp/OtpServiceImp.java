package com.dansaki.com.temisplacebackend.services.otp;

import com.dansaki.com.temisplacebackend.config.AppConfig;
import com.dansaki.com.temisplacebackend.data.models.Roles;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.data.models.VerificationToken;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.services.verificationToken.VerificationTokenService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class OtpServiceImp implements OtpService{
    private final VerificationTokenService verificationTokenService;
    private final JavaMailSender mailSender;
    @Override
    public ApiResponse sendOtp(String emailAddress) {
        String generatedOtp = verificationTokenService.generateVerificationToken(3);
        VerificationToken verificationToken = VerificationToken.builder()
                .verificationToken(generatedOtp)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15L))
                .emailAddress(emailAddress)
                .build();


        verificationTokenService.save(verificationToken);


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(AppConfig.MAIL_SENDER_ACCOUNT);
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject(GenerateApiResponse.VERIFY_THAT_ITS_YOU);
        simpleMailMessage.setText( "Kindly verify your account with this Otp code : " + verificationToken.getVerificationToken());
        System.out.println("I got here");
        mailSender.send(simpleMailMessage);

        return ApiResponse.builder()
                .data(GenerateApiResponse.OTP_SENT_SUCCESSFULLY)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }
}
