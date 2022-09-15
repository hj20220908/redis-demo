package com.example.redis.controller;

import com.example.redis.dto.RedisReqDto;
import com.example.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/redis")
    public boolean create(@RequestBody RedisReqDto reqDto) {

        redisService.setRedisValue(reqDto.getKey(), reqDto.getValue());

        return true;
    }

    @GetMapping("/redis")
    public String read(@RequestParam String key) {

        return redisService.getRedisValue(key);
    }

    @PutMapping("/redis")
    public boolean update(@RequestBody RedisReqDto reqDto) {

        redisService.updateRedisValue(reqDto.getKey(), reqDto.getValue());

        return true;
    }

    @DeleteMapping("/redis")
    public boolean delete(@RequestBody RedisReqDto reqDto) {

        redisService.deleteRedisValue(reqDto.getKey());
        return true;
    }
}
