package com.dansaki.com.temisplacebackend.services.token;


import com.dansaki.com.temisplacebackend.data.models.Token;
import com.dansaki.com.temisplacebackend.data.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenServiceImp implements TokenService{

    private final TokenRepository tokenRepository;
    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findTokenByUserEmailAddress(String emailAddress) {
        return tokenRepository.findTokenByUserEmailAddress(emailAddress);
    }

    @Override
    public Optional<Token> findTokenByJwt(String jwt) {
        return tokenRepository.findTokenByJwt(jwt);
    }
}
