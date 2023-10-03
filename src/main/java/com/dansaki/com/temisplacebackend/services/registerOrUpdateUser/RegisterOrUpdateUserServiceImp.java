package com.dansaki.com.temisplacebackend.services.registerOrUpdateUser;

import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.RegisterOrUpdateUserRequest;
import com.dansaki.com.temisplacebackend.dtos.request.RegistrationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.UserProfileUpdateRequest;
import com.dansaki.com.temisplacebackend.services.authentication.RegistrationService;
import com.dansaki.com.temisplacebackend.services.user.UpdateUserProfileService;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegisterOrUpdateUserServiceImp implements RegisterOrUpdateUserService{

    private final RegistrationService registrationService;
    private final UpdateUserProfileService updateUserProfileService;
    private final UserService userService;

    private final ModelMapper modelMapper;

    @SneakyThrows
    @Override
    public ApiResponse registerOrUpdateUser(RegisterOrUpdateUserRequest registerOrUpdateUserRequest) {
        if(userAlreadyExists(registerOrUpdateUserRequest.getEmailAddress())){
            UserProfileUpdateRequest userProfileUpdateRequest = modelMapper.map(registerOrUpdateUserRequest, UserProfileUpdateRequest.class);
            updateUserProfileService.updateUserProfile(userProfileUpdateRequest);
        }
        else{
            RegistrationRequest registrationRequest = modelMapper.map(registerOrUpdateUserRequest, RegistrationRequest.class);
            registrationService.register(registrationRequest);
        }
        return GenerateApiResponse.incorrectDetails(GenerateApiResponse.INCORRECT_DETAILS);
    }

    private boolean userAlreadyExists(String emailAddress) {
        return userService.findUserByEmailAddress(emailAddress)!=null;
    }
}
