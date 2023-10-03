package com.dansaki.com.temisplacebackend.services.registerOrUpdateUser;

import com.dansaki.com.temisplacebackend.dtos.request.RegisterOrUpdateUserRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface RegisterOrUpdateUserService {
    ApiResponse registerOrUpdateUser(RegisterOrUpdateUserRequest registerOrUpdateUserRequest);
}
