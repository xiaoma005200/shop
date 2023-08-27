package com.xiaoma.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类,为了方便操作redis
 */
@Component
public class RedisUtils {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 向redis中存入string类型数据
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置key,value的同时设置过期时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean set(String key, Object value, long timeout, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 利用setnx命令的特性可以实现分布式锁,获取一个锁同时设置它的过期时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return 如果setnx成功设置了key,返回true,如果redis中key已经存在,setnx返回false
     */
    public boolean setNX(String key, Object value, long timeout, TimeUnit timeUnit) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key取出redis中的string类型数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 向redis中存入hash类型数据
     *
     * @param field
     * @param key
     * @param value
     * @return
     */
    public boolean hset(String key, Object field, Object value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 根据key和field取出redis中的hash类型的数据
     *
     * @param key
     * @param field
     * @return
     */
    public Object get(String key, Object field) {
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 通用方法:根据key删除对应的数据
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 通用方法:判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exist(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 为某个key设置过期时间
     *
     * @param key
     * @param timeout
     * @return
     */
    public boolean expire(String key, long timeout) {
        try {
            return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查看key的剩余存活时间
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
