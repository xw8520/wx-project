package com.cache.impl;

import com.cache.EhCacheManager;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by Admin on 2016/1/9.
 */
public class EhCacheManagerImpl implements EhCacheManager {

    private CacheManager cacheManager;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private String cacheName;

    public void put(String key, Object value, int seconds) {
        Element el = new Element(key, value);
        el.setTimeToLive(seconds);
        getCache().put(el);
    }

    public void put(String key, Object value, int timeToLiveSeconds, int timeToIdleSeconds) {
        Element el = new Element(key, value);
        el.setTimeToLive(timeToLiveSeconds);
        el.setTimeToIdle(timeToIdleSeconds);
        getCache().put(el);
    }

    public <T> T get(String key, Class<T> classType) {
       Element el=getCache().get(key);
        if (el != null) {
            return  (T)el.getObjectValue();
        }
        return null;
    }

    private Cache getCache() {
        Cache cache = null;
        try {
            cache = this.cacheManager.getCache(this.cacheName);
            if (cache == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }
}
