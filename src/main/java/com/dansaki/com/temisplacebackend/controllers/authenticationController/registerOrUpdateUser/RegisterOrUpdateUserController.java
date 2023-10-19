package com.dansaki.com.temisplacebackend.controllers.authenticationController.registerOrUpdateUser;


import com.dansaki.com.temisplacebackend.dtos.request.RegisterOrUpdateUserRequest;
import com.dansaki.com.temisplacebackend.services.registerOrUpdateUser.RegisterOrUpdateUserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RegisterOrUpdateUserController {

    private final RegisterOrUpdateUserService registerOrUpdateUserService;

    @PostMapping("registerOrUpdateUser")
    public ResponseEntity<ApiResponse> registerOrUpdateUser(@RequestBody RegisterOrUpdateUserRequest registerOrUpdateUserRequest){
        return new ResponseEntity<>(registerOrUpdateUserService.registerOrUpdateUser(registerOrUpdateUserRequest), HttpStatus.OK);
    }
}
