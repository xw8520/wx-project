package com.cache.impl;

import com.cache.RedisCacheManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Admin on 2016/1/9.
 */
public class RedisCacheManagerImpl implements RedisCacheManager {

    @Resource
    ShardedJedisPool jedisPool;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void put(String key, Object value, int seconds) {
        ShardedJedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, mapper.writeValueAsString(value));
            jedis.expire(key, seconds);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Override
    public void put(String key, Object value, long unixTime) {
        ShardedJedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, mapper.writeValueAsString(value));
            jedis.expireAt(key, unixTime);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Override
    public void put(String key, String value, int seconds) {
        ShardedJedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.expire(key, seconds);
        jedisPool.returnResourceObject(jedis);
    }

    @Override
    public void put(String key, String value, long unixTime) {
        ShardedJedis jedis = jedisPool.getResource();
        jedis.expireAt(key, unixTime);
        jedisPool.returnResourceObject(jedis);
    }

    @Override
    public void put(byte[] key, byte[] value, int seconds) {
        ShardedJedis jedis = jedisPool.getResource();
        jedis.expire(key, seconds);
        jedisPool.returnResourceObject(jedis);
    }

    @Override
    public void put(byte[] key, byte[] value, long unixTime) {
        ShardedJedis jedis = jedisPool.getResource();
        jedis.expireAt(key, unixTime);
        jedisPool.returnResourceObject(jedis);
    }

    @Override
    public byte[] get(byte[] key) {
        ShardedJedis jedis = jedisPool.getResource();
        byte[] val = jedis.get(key);
        jedisPool.returnResourceObject(jedis);
        return val;
    }

    @Override
    public <T> T get(String key, Class<T> classType) {
        ShardedJedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        if (StringUtils.isEmpty(value)) return null;
        T val = null;
        try {
            val = (T) mapper.readValue(value, classType);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
        return val;
    }
}
