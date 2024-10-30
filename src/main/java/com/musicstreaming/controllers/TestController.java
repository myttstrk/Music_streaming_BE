package com.musicstreaming.controllers;

import com.musicstreaming.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    public String setData(@RequestParam String key, @RequestParam String value) {
        redisService.saveData(key, value);
        return "Data saved successfully!";
    }

    @GetMapping("/get")
    public String getData(@RequestParam String key) {
        return "Value: " + redisService.getData(key);
    }

    @GetMapping("/delete")
    public String deleteData(@RequestParam String key) {
        redisService.deleteData(key);
        return "Data deleted successfully!";
    }
}
