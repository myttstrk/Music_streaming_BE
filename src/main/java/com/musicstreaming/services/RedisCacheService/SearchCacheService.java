package com.musicstreaming.services.RedisCacheService;

import com.musicstreaming.models.Song;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public SearchCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheSearchResults(String keyword, List<Song> songs) {
        String key = "search:results:" + keyword.toLowerCase();
        redisTemplate.opsForValue().set(key, songs);
    }

    public List<Song> getSearchResults(String keyword) {
        String key = "search:results:" + keyword.toLowerCase();
        return (List<Song>) redisTemplate.opsForValue().get(key);
    }
}
