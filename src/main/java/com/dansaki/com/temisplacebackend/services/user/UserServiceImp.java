package com.dansaki.com.temisplacebackend.services.user;

import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.data.repositories.UserRepository;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user ) {
     return userRepository.save(user);
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddressIgnoreCase(emailAddress).orElse(null);
    }

    @Override
    public Optional<User> findByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    public List<User> findAllRegisteredUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findPaginatedUserList(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        Page<User> listOfPaginatedUsers = userRepository.findAll(pageable);
        return listOfPaginatedUsers.getContent();
    }
}
