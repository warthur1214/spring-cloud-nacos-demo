package com.warthur.nacos.demo.infrastructure.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author warthur
 */
@Component
public class StringRedisCache extends AbstractRedisCache<String> {

    @Autowired
    @Qualifier("stringRedisTemplate")
    protected RedisTemplate<String, String> redisTemplate;

    @Override
    protected RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public boolean setNx(String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            result = operations.setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        String result = null;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            result = operations.getAndSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void set(String key, String value) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void set(final String key, String value, Long expireTime, TimeUnit unit) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public void hSet(String key, String field, String value) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.put(key, field, value);
    }

    @Override
    public String hGet(String key, String field) {
        if (!exists(key)) {
            return null;
        }
        if (!hKey(key, field)) {
            return null;
        }
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.get(key, field);
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        if (!exists(key)) {
            return null;
        }

        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.entries(key);
    }

    @Override
    public void hDel(String key, String field) {
        if (!exists(key)) {
            return;
        }
        if (!hKey(key, field)) {
            return;
        }
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.delete(key, field);
    }

    @Override
    public Boolean hKey(String key, String field) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.hasKey(key, field);
    }

    @Override
    public void lPush(String key, String value) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }
    @Override
    public String leftPop(String key) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        return list.leftPop(key);
    }

    @Override
    public void rightPush(String key, String value) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }
}
