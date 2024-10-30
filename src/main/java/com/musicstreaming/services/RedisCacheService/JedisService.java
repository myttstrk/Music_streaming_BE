package com.musicstreaming.services.RedisCacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class JedisService {
    @Autowired
    private Jedis jedis;

    public void saveData(String key, String value) {
        jedis.set(key, value);
    }

    public String getData(String key) {
        return jedis.get(key);
    }
}
