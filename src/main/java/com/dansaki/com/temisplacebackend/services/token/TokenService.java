package com.dansaki.com.temisplacebackend.services.token;

import com.dansaki.com.temisplacebackend.data.models.Token;

import java.util.Optional;

public interface TokenService {

    Token saveToken(Token token);

    Optional<Token> findTokenByUserEmailAddress(String emailAddress);

    Optional<Token> findTokenByJwt(String jwt);
}
