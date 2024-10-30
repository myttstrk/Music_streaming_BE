package com.musicstreaming.controllers;

import com.musicstreaming.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/test-redis")
    public String testRedis() {
        redisService.saveData("testKey", "Hello, Redis!");
        return redisService.getData("testKey");
    }
}
