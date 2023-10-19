package com.dansaki.com.temisplacebackend.data.repositories;


import com.dansaki.com.temisplacebackend.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress);
    List<User> findAllByRegistrationDate(LocalDate registrationDate);
}
