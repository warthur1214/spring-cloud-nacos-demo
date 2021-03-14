package com.warthur.nacos.demo.infrastructure.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author warthur
 */
@Slf4j
public abstract class AbstractRedisCache<T> implements Cache<T> {

    /**
     * 获取bean对象
     * @return getRedisTemplate()
     */
    protected abstract RedisTemplate<String, T> getRedisTemplate();

    @Override
    public List<Object> executePipeline(CachePipeline pipeline) {
        RedisConnectionFactory factory = getRedisTemplate().getConnectionFactory();
        assert factory != null;
        RedisConnection connection = RedisConnectionUtils.getConnection(factory);
        List<Object> result = null;
        try {
            connection.openPipeline();
            pipeline.executeCommand(connection);
            result = connection.closePipeline();
        } catch (Exception e) {
            log.error("批量执行redis命令失败！", e);
        } finally {
            RedisConnectionUtils.releaseConnection(connection, factory, false);
        }

        return result;
    }

    @Override
    public void multi() {
        getRedisTemplate().multi();
    }

    @Override
    public List<Object> execute() {
        return getRedisTemplate().exec();
    }

    @Override
    public Set<String> keys(String pattern) {
        return getRedisTemplate().keys(pattern);
    }

    @Override
    public long size(String key) {
        if (!exists(key)) {
            return 0;
        }

        ListOperations<String, T> list = getRedisTemplate().opsForList();
        return list.size(key);
    }

    @Override
    public void expire(String key, long seconds) {
        getRedisTemplate().expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public long getExpire(String key) {
        return getRedisTemplate().getExpire(key);
    }

    @Override
    public void delete(String... keys) {
        for (String key : keys) {
            if (key != null && exists(key)) {
                getRedisTemplate().delete(key);
            }
        }
    }

    @Override
    public void deletePattern(String pattern) {
        Set<String> keys = getRedisTemplate().keys(pattern);
        if (keys.size() > 0) {
            getRedisTemplate().delete(keys);
        }
    }

    @Override
    public boolean exists(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return getRedisTemplate().hasKey(key);
    }

    @Override
    public long incr(String key) {
        ValueOperations<String, T> values = getRedisTemplate().opsForValue();
        return values.increment(key, 1);
    }

    public Long hIncr(String key, String field) {
        return hIncr(key, field, 1);
    }

    @Override
    public Long hIncr(String key, String field, long needle) {
        HashOperations<String, String, Long> hash = getRedisTemplate().opsForHash();
        return hash.increment(key, field, needle);
    }

    @Override
    public void add(String key, T... value) {
        SetOperations<String, T> set = getRedisTemplate().opsForSet();
        set.add(key, value);
    }

    @Override
    public void sRem(String key, T value) {
        SetOperations<String, T> set = getRedisTemplate().opsForSet();
        set.remove(key, value);
    }

    @Override
    public Set<T> setMembers(String key) {
        SetOperations<String, T> set = getRedisTemplate().opsForSet();
        return set.members(key);
    }

    @Override
    public List<T> lRange(String key, long start, long end) {
        ListOperations<String, T> list = getRedisTemplate().opsForList();
        return list.range(key, start, end);
    }

    @Override
    public void zAdd(String key, T value, double index) {
        ZSetOperations<String, T> zset = getRedisTemplate().opsForZSet();
        zset.add(key, value, index);
    }

    @Override
    public Set<T> rangeByScore(String key, double start, double end) {
        ZSetOperations<String, T> zset = getRedisTemplate().opsForZSet();
        return zset.rangeByScore(key, start, end);
    }
}
