package com.dansaki.com.temisplacebackend.controllers.registrationController;


import com.dansaki.com.temisplacebackend.dtos.request.RegistrationRequest;
import com.dansaki.com.temisplacebackend.services.authentication.RegistrationService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class RegistrationController {

    private final RegistrationService registrationService;
    @SneakyThrows
    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(registrationService.register(registrationRequest), HttpStatus.OK);
    }
}
