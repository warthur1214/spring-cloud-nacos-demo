package com.warthur.nacos.demo.infrastructure.config.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Cache<T> {

    List<Object> executePipeline(CachePipeline pipeline);

    void multi();

    List<Object> execute();

    Set<String> keys(final String pattern);

    long size(String key);

    boolean setNx(final String key, T value);

    T getSet(final String key, T value);

    void expire(final String key, long seconds);

    long getExpire(final String key);

    void set(final String key, T value);

    default void set(final String key, T value, Long expireTime) {
        set(key, value, expireTime, TimeUnit.SECONDS);
    }

    void set(final String key, T value, Long expireTime, TimeUnit unit);

    void delete(final String... keys);

    void deletePattern(final String pattern);

    boolean exists(final String key);

    long incr(final String key);

    T get(final String key);

    void hSet(String key, String field, T value);

    T hGet(String key, String field);

    Map<String, T> hGetAll(String key);

    void hDel(String key, String field);

    Boolean hKey(String key, String field);

    Long hIncr(String key, String field, long needle);

    void lPush(String key, T value);

    List<T> lRange(String key, long start, long end);

    void add(String key, T... value);

    void sRem(String key, T value);

    Set<T> setMembers(String key);

    void zAdd(String key, T value, double index);

    Set<T> rangeByScore(String key, double start, double end);

    T leftPop(String key);

    void rightPush(String key, T value);
}
