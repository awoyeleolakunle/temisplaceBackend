package com.dansaki.com.temisplacebackend.services.authentication;


import com.dansaki.com.temisplacebackend.data.models.Roles;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.RegistrationRequest;
import com.dansaki.com.temisplacebackend.exception.UserRegistrationException;
import com.dansaki.com.temisplacebackend.security.JwtService;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


public ApiResponse register(RegistrationRequest registrationRequest) throws UserRegistrationException {
    boolean isRegisteredUser = userService.findUserByEmailAddress(registrationRequest.getEmailAddress()) != null;
    if (isRegisteredUser) return GenerateApiResponse.alreadyCreated(GenerateApiResponse.USER_ALREADY_EXIST);

        User user = modelMapper.map(registrationRequest, User.class);
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(Roles.CUSTOMER);
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        User savedUser =   userService.save(user);

    UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmailAddress());
    String jwt = jwtService.generateToken(userDetails);

    return GenerateApiResponse.createdResponse(GenerateApiResponse.BEARER+jwt);
}

}
