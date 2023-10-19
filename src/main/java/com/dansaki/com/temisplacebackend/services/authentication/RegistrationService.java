package com.dansaki.com.temisplacebackend.services.authentication;


import com.dansaki.com.temisplacebackend.data.enums.UserStatus;
import com.dansaki.com.temisplacebackend.data.models.Roles;
import com.dansaki.com.temisplacebackend.data.models.Token;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.LoginRequest;
import com.dansaki.com.temisplacebackend.dtos.request.RegistrationRequest;
import com.dansaki.com.temisplacebackend.exception.UserRegistrationException;
import com.dansaki.com.temisplacebackend.security.JwtService;
import com.dansaki.com.temisplacebackend.services.token.TokenService;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


public ApiResponse register(RegistrationRequest registrationRequest) throws UserRegistrationException {
    boolean isRegisteredUser = userService.findUserByEmailAddress(registrationRequest.getEmailAddress()) != null;
    if (isRegisteredUser) return GenerateApiResponse.alreadyCreated(GenerateApiResponse.USER_ALREADY_EXIST);

        User user = modelMapper.map(registrationRequest, User.class);
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(Roles.CUSTOMER);
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        user.setRegistrationTime(LocalTime.now());
        user.setUserStatus(UserStatus.SUSPEND);
        User savedUser =   userService.save(user);

    UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmailAddress());
    String jwt = jwtService.generateToken(userDetails);

    return GenerateApiResponse.createdResponse(GenerateApiResponse.BEARER+jwt);
}


    public ApiResponse login(LoginRequest loginRequest){
        System.out.println(loginRequest.getEmailAddress());
        System.out.println("i entered here ");
    authenticateUser(loginRequest);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmailAddress());
        System.out.println("I'm the userDetails" + userDetails.getUsername());

        if(userDetails== null){ return GenerateApiResponse.incorrectDetails(GenerateApiResponse.NO_USER_FOUND);}
        String jwt = jwtService.generateToken(userDetails);
        saveToken(jwt, loginRequest.getEmailAddress());
        return GenerateApiResponse.okResponse("Bearer "+jwt);
    }

    private void authenticateUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));
}

    private void saveToken(String jwt, String emailAddress) {
        Token token = Token.builder()
                .jwt(jwt)
                .isExpired(false)
                .isRevoked(false)
                .userEmailAddress(emailAddress)
                .build();
        tokenService.saveToken(token);
    }
}
