package com.cache;

/**
 * Created by Admin on 2016/1/9.
 */
public interface RedisCacheManager {

    void put(String key, Object value, int seconds);

    void put(String key, Object value, long unixTime);

    void put(String key, String value, int seconds);

    void put(String key, String value, long unixTime);

    void put(byte[] key, byte[] value, int seconds);

    void put(byte[] key, byte[] value, long unixTime);

    byte[] get(byte[] key);

    String get(String key);

    <T> T get(String key, Class<T> classType);
}
