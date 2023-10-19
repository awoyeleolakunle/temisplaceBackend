package com.dansaki.com.temisplacebackend.services.authentication;

import com.dansaki.com.temisplacebackend.data.enums.UserStatus;
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

import java.time.LocalTime;
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
    public void testThatAdminCanRegisterUser2() throws UserRegistrationException {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setPassword("password");
        registrationRequest.setEmailAddress("emailAddress2");
        registrationRequest.setFirstName("secondName");
        registrationRequest.setLastName("lastSecondName");
        registrationRequest.setCountry("United Kingdom");
        registrationRequest.setCity("London");

       ApiResponse  response=  registrationService.register(registrationRequest);

        System.out.println(response.getData());

        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void createSuperAdmin() throws UserRegistrationException {
//        RegistrationRequest registrationRequest = new RegistrationRequest();
//        registrationRequest.setPassword("password");
//        registrationRequest.setEmailAddress("emailAddress");
//        registrationRequest.setFirstName("firstAdmin");
//        registrationRequest.setLastName("lastNameAdin");
//        ApiResponse  response=  registrationService.register(registrationRequest);
//
//        System.out.println(response.getData());
//
//        assertThat(response.isSuccessful()).isTrue();
        User userAdmin = new User();
        userAdmin.setUserStatus(UserStatus.ACTIVE);
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(Roles.ADMIN);
        userAdmin.setRoles(userRoles);
        userAdmin.setPhoneNumber("+43524345647");
        userAdmin.setCity("London");
        userAdmin.setCountry("United Kingdom");
        userAdmin.setPostCode("101101101");
        userAdmin.setLastName("Samuel");
        userAdmin.setFirstName("Morenikeji");
        userAdmin.setRegistrationTime(LocalTime.now());
        userAdmin.setPassword(passwordEncoder.encode("password"));
        userAdmin.setEmailAddress("olakunleawoyele@gmail.com");
        userService.save(userAdmin);
    }

    @Test
    public void createUnitAccount(){
        User userAdmin = new User();
        userAdmin.setUserStatus(UserStatus.ACTIVE);
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(Roles.UNIT);
        userAdmin.setRoles(userRoles);
        userAdmin.setPhoneNumber("+43453456798");
        userAdmin.setCity("London");
        userAdmin.setCountry("United Kingdom");
        userAdmin.setPostCode("101101101");
        userAdmin.setLastName("FABZ");
        userAdmin.setFirstName("MAN");
        userAdmin.setRegistrationTime(LocalTime.now());
        userAdmin.setPassword(passwordEncoder.encode("password"));
        userAdmin.setEmailAddress("emailAddress@gmail.com");
        userService.save(userAdmin);
    }


    @Test
    public void testThatAdminCanRegisterUser() throws UserRegistrationException {

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
}