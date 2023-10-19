package com.dansaki.com.temisplacebackend.services.user;

import com.dansaki.com.temisplacebackend.dtos.request.UserProfileUpdateRequest;
import com.dansaki.com.temisplacebackend.dtos.request.UserStatusUpdateRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface UpdateUserProfileService {
    ApiResponse updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest);

    ApiResponse updateUserStatus(UserStatusUpdateRequest userStatusUpdateRequest);
}
