package com.musicstreaming.services.RedisCacheService;

import com.musicstreaming.models.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongCacheService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String POPULAR_SONGS_KEY = "popular:songs";
    private static final String LATEST_SONGS_KEY = "latest:songs";
    public void cachePopularSongs(List<Song> songs){
        redisTemplate.opsForValue().set(POPULAR_SONGS_KEY,songs);
    }
    public List<Song> getPopularSongs(){
        return (List<Song>) redisTemplate.opsForValue().get(POPULAR_SONGS_KEY);
    }
    public void cacheLatestSongs(List<Song> latestSongs) {
        redisTemplate.opsForValue().set(LATEST_SONGS_KEY, latestSongs);
    }
    public List<Song> getLatestSongs() {
        return (List<Song>) redisTemplate.opsForValue().get(LATEST_SONGS_KEY);
    }
}
