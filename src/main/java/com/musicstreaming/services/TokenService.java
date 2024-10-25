package com.musicstreaming.services;

import com.musicstreaming.models.Token;
import com.musicstreaming.models.User;

public interface TokenService {
    Token addTokenEndRefreshToken(User user, String token, boolean isMobile);


}
