package com.warthur.nacos.demo.config.redis;

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
public class DataRedisCache extends AbstractRedisCache<Object> {

    @Autowired
    @Qualifier("jsonRedisTemplate")
    protected RedisTemplate<String, Object> redisTemplate;

    @Override
    protected RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public boolean setNx(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            result = operations.setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object getSet(String key, Object value) {
        Object result = null;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            result = operations.getAndSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void set(String key, Object value) {
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void set(String key, Object value, Long expireTime, TimeUnit unit) {
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public void hSet(String key, String field, Object value) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        hash.put(key, field, value);
    }

    @Override
    public Object hGet(String key, String field) {
        if (!exists(key)) {
            return null;
        }
        if (!hKey(key, field)) {
            return null;
        }
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, field);
    }

    @Override
    public Map<String, Object> hGetAll(String key) {
        if (!exists(key)) {
            return null;
        }

        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
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
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        hash.delete(key, field);
    }

    @Override
    public Boolean hKey(String key, String field) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        return hash.hasKey(key, field);
    }

    @Override
    public void lPush(String key, Object value) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }

    @Override
    public Object leftPop(String key) {
        ListOperations<String, Object> list = redisTemplate.opsForList();

        return list.leftPop(key);
    }

    @Override
    public void rightPush(String key, Object value) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }
}
