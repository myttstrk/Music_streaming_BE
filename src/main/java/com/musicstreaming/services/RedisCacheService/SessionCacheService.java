package com.musicstreaming.services.RedisCacheService;

import com.musicstreaming.models.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SessionCacheService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final long SESSION_EXPIRATION = 3600; // Thời gian tồn tại 1 giờ

    public SessionCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void createSession(String sessionId, Token userSession) {
        String key = "session:user:" + sessionId;
        redisTemplate.opsForValue().set(key, userSession, SESSION_EXPIRATION, TimeUnit.SECONDS);
    }

    public Token getSession(String sessionId) {
        String key = "session:user:" + sessionId;
        return (Token) redisTemplate.opsForValue().get(key);
    }

    public void deleteSession(String sessionId) {
        String key = "session:user:" + sessionId;
        redisTemplate.delete(key);
    }
}
