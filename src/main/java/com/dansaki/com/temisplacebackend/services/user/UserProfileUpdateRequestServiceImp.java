package com.dansaki.com.temisplacebackend.services.user;

import com.dansaki.com.temisplacebackend.data.enums.UserStatus;
import com.dansaki.com.temisplacebackend.data.models.Roles;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.UserProfileUpdateRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserProfileUpdateRequestServiceImp implements UpdateUserProfileService{

    private final UserService userService;
    @Override
    public ApiResponse updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest) {

        if(findUser(userProfileUpdateRequest.getEmailAddress())==null){ return GenerateApiResponse.userNotFound(GenerateApiResponse.NO_USER_FOUND);}

        updateUser(userProfileUpdateRequest);
        return GenerateApiResponse.createdResponse(GenerateApiResponse.USER_CREATED_SUCCESSFULLY);
    }

    private void updateUser(UserProfileUpdateRequest userProfileUpdateRequest) {

        User user = findUser(userProfileUpdateRequest.getEmailAddress());

        if(userProfileUpdateRequest.getUserStatus()!=null){
            System.out.println( "I'm the user status string " + userProfileUpdateRequest.getUserStatus());
            user.setUserStatus(UserStatus.valueOf(userProfileUpdateRequest.getUserStatus()));
        }
        if(userProfileUpdateRequest.getRoles()!=null){
            Set<Roles> setOfRoles = new HashSet<>();
            setOfRoles.add(Roles.valueOf(userProfileUpdateRequest.getRoles()));
            user.setRoles(setOfRoles);
        }

        if(userProfileUpdateRequest.getCity()!=null){
            user.setCity(userProfileUpdateRequest.getCity());
        }
        if(userProfileUpdateRequest.getFirstName()!=null){
            user.setFirstName(userProfileUpdateRequest.getFirstName());
        }
        if(userProfileUpdateRequest.getLastName()!=null){
            user.setLastName(userProfileUpdateRequest.getLastName());
        }
        if(userProfileUpdateRequest.getCountry()!=null){
            user.setCountry(userProfileUpdateRequest.getCountry());
        }
        if(userProfileUpdateRequest.getPhoneNumber()!=null){
            user.setPhoneNumber(userProfileUpdateRequest.getPhoneNumber());
        }
        if(userProfileUpdateRequest.getPostCode()!=null){
            user.setPostCode(userProfileUpdateRequest.getPostCode());
        }

        userService.save(user);
    }

    private User findUser (String emailAddress){
        return userService.findByEmailAddress(emailAddress).orElse(null);
    }
}
