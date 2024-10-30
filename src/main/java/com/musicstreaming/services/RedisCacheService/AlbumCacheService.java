    package com.musicstreaming.services.RedisCacheService;

    import com.musicstreaming.models.Album;
    import com.musicstreaming.models.Song;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class AlbumCacheService {

        private final RedisTemplate<String, Object> redisTemplate;
        private static final String POPULAR_ALBUMS_KEY = "popular:albums";
        private static final String LATEST_ALBUMS_KEY = "latest:albums";

        public void cachePopularAlbums(List<Album> albums){
            redisTemplate.opsForValue().set(POPULAR_ALBUMS_KEY,albums);
        }
        public List<Album> getPopularAlbums(){
            return (List<Album>) redisTemplate.opsForValue().get(POPULAR_ALBUMS_KEY);
        }
        public void cacheLatestAlbums(List<Album> latestAlbums) {
            redisTemplate.opsForValue().set(LATEST_ALBUMS_KEY, latestAlbums);
        }
        public List<Album> getLatestAlbums() {
            return (List<Album>) redisTemplate.opsForValue().get(LATEST_ALBUMS_KEY);
        }
    }
