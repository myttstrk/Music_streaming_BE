package com.musicstreaming.controllers;

import com.musicstreaming.services.RedisCacheService.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JedisController {
    @Autowired
    private JedisService myService;

    @PostMapping("/saveJedis")
    public String save(@RequestParam String key, @RequestParam String value) {
        myService.saveData(key, value);
        return "Data saved successfully!";
    }

    @GetMapping("/getJedis")
    public String get(@RequestParam String key) {
        return myService.getData(key);
    }
}
