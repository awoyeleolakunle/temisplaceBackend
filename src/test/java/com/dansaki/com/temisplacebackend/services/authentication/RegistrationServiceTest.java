package com.dansaki.com.temisplacebackend.services.authentication;

import com.dansaki.com.temisplacebackend.data.models.Roles;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.RegistrationRequest;
import com.dansaki.com.temisplacebackend.exception.UserRegistrationException;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationServiceTest {


    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testThatAdminCanRegister() throws UserRegistrationException {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setPassword("password");
        registrationRequest.setEmailAddress("emailAddress1");
        registrationRequest.setFirstName("firstName");
        registrationRequest.setLastName("lastName");
        registrationRequest.setCountry("United Kingdom");
        registrationRequest.setCity("London");
       ApiResponse  response=  registrationService.register(registrationRequest);

        System.out.println(response.getData());

        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void createSuperAdmin() throws UserRegistrationException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setPassword("password");
        registrationRequest.setEmailAddress("emailAddress");
        registrationRequest.setFirstName("firstAdmin");
        registrationRequest.setLastName("lastNameAdin");
        ApiResponse  response=  registrationService.register(registrationRequest);

        System.out.println(response.getData());

        assertThat(response.isSuccessful()).isTrue();

    }
}