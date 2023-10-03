package com.dansaki.com.temisplacebackend.controllers.userController;


import com.dansaki.com.temisplacebackend.dtos.request.UserProfileUpdateRequest;
import com.dansaki.com.temisplacebackend.services.user.UpdateUserProfileService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UpdateUserProfileController {

    private final UpdateUserProfileService updateUserProfileService;

    @PostMapping("updateUser")
    public ResponseEntity<ApiResponse> updateUserProfile(@RequestBody UserProfileUpdateRequest userProfileUpdateRequest){
        return new ResponseEntity<>(updateUserProfileService.updateUserProfile(userProfileUpdateRequest), HttpStatus.OK);
    }
}
