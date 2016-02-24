package com.cache;

/**
 * Created by Admin on 2016/1/9.
 */
public interface EhCacheManager {
    void put(String key, Object value, int seconds);
    void put(String key, Object value, int timeToLiveSeconds, int timeToIdleSeconds);
    <T>T get(String key, Class<T> classType);
}
