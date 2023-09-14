package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository <Token, Long> {

    Optional<Token> findTokenByUserEmailAddress(String userEmailAddress);

    Optional<Token> findTokenByJwt(String jwt);
}
