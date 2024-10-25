package com.musicstreaming.services.impl;

import com.musicstreaming.exceptions.payload.DataNotFoundException;
import com.musicstreaming.models.Token;
import com.musicstreaming.models.User;
import com.musicstreaming.repositories.TokenRepository;
import com.musicstreaming.repositories.UserRepository;
import com.musicstreaming.services.TokenService;
import com.musicstreaming.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private static final int MAX_TOKENS=2;
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.expiration-refresh-token}")
    private int expirationRefreshToken;


    @Override
    public Token addTokenEndRefreshToken(User user, String token, boolean isMobile) {
        List<Token> userTokens = tokenRepository.findByUser(user);
        int tokenCount= userTokens.size();
        if(tokenCount>MAX_TOKENS){
            boolean hasNoMobileToken = !userTokens.stream().allMatch(Token::isMobile);
            Token tokenToRemove;
            if(hasNoMobileToken){
                tokenToRemove=userTokens.stream().filter(userToken ->!userToken.isMobile()).findFirst().orElse(userTokens.get(0));
            }else{
                tokenToRemove=userTokens.get(0);
            }
            tokenRepository.delete(tokenToRemove);
        }
        Token newToken=Token.builder()
                .user(user)
                .refreshToken(UUID.randomUUID().toString())
                .tokenType("Bearer")
                .expireTime(Instant.now().plusMillis(expiration))
                .refreshExpirationTime(Instant.now().plusMillis(expirationRefreshToken))
                .revoked(false)
                .expired(false)
                .token(token)
                .isMobile(isMobile)
                .build();
            return tokenRepository.save(newToken);
    }
    public Token verifyRefreshToken(String refreshToken){
        Token token= tokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new DataNotFoundException(MessageKeys.NOT_FOUND));
        if (token.getExpireTime().compareTo(Instant.now()) < 0) {
            tokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }
        return token;

    }
}
