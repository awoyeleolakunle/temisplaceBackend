package com.dansaki.com.temisplacebackend.controllers.authenticationController.loginController;


import com.dansaki.com.temisplacebackend.dtos.request.LoginRequest;
import com.dansaki.com.temisplacebackend.services.authentication.RegistrationService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class LoginController {
    private final RegistrationService registrationService;

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest){
        System.out.println("I'm in the controller. I'm the login emailAddress "+ loginRequest.getEmailAddress());
        return new ResponseEntity<>(registrationService.login(loginRequest), HttpStatus.OK);
    }

}
