package com.dansaki.com.temisplacebackend.services.user;

import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User save(User user);

    User findUserByEmailAddress(String emailAddress);

    List<User> findAllRegisteredUsers();

    List<User> findPaginatedUserList(PaginationRequest paginationRequest);
}
