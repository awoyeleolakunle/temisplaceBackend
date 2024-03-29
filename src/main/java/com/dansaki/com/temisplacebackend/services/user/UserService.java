package com.dansaki.com.temisplacebackend.services.user;

import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {


    User save(User user);

    User findUserByEmailAddress(String emailAddress);

    Optional<User> findByEmailAddress(String emailAddress);
    List<User> findAllRegisteredUsers();

    List<User> findPaginatedUserList(PaginationRequest paginationRequest);

    List<User> findAllRegisteredCustomerForAParticularDate(LocalDate registrationDate);


    Optional<User> findUserById(Long userId);

}
