package com.dansaki.com.temisplacebackend.controllers.userController;


import com.dansaki.com.temisplacebackend.dtos.request.UserStatusUpdateRequest;
import com.dansaki.com.temisplacebackend.services.user.UpdateUserProfileService;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UpdateUserStatusController {

    private  final UpdateUserProfileService updateUserProfileService;


    @PostMapping("userStatusUpdate")
    public ResponseEntity<ApiResponse> updateUserStatus(@RequestBody UserStatusUpdateRequest userStatusUpdateRequest){
        return new ResponseEntity<>(updateUserProfileService.updateUserStatus(userStatusUpdateRequest), HttpStatus.OK);
    }
}
