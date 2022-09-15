package com.example.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RedisService {

    private final int LIMIT_TIME = 3 * 60; // 3분

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * create
     * @param key
     * @param value
     */
    public void setRedisValue(String key, String value) {

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key, value);  // redis set 명령어와 동일함
//        stringValueOperations.set(key, vlaue, LIMIT_TIME);  // 만료시간 지정 예시

    }

    /**
     * read
     * @param key
     * @return
     */
    public String getRedisValue(String key) {

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String value = stringValueOperations.get(key);// redis get 명령어와 동일함
        if (value == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return value;
    }

    /**
     * update
     * @param key
     * @param value
     */
    public void updateRedisValue(String key, String value) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.getAndSet(key, value);
    }

    /**
     * delete
     * @param key
     */
    public void deleteRedisValue(String key) {
        stringRedisTemplate.delete(key);
    }
}
