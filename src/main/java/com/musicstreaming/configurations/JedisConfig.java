package com.musicstreaming.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration

public class JedisConfig {
    @Bean
    public JedisPool jedisPool() {
        // Cấu hình JedisPool với URI của Redis
        return new JedisPool("redis://default:iEvuXo6mXug2l00AYNnxopG9edXxERwr@redis-17422.c302.asia-northeast1-1.gce.redns.redis-cloud.com:17422");
    }

    @Bean
    public Jedis jedis(JedisPool jedisPool) {
        return jedisPool.getResource();
    }
}
