package com.flame.flameoss.global.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

@Service
public class EhCacheService {

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    public void put(String cacheName, Object key, Object value) {
        Cache cache = ehCacheCacheManager.getCache(cacheName);
        cache.put(key, value);
    }

    public Object get(String cacheName, Object key) {
        Cache cache = ehCacheCacheManager.getCache(cacheName);
        Cache.ValueWrapper valueWrapper = cache.get(key);
        return valueWrapper == null ? null : valueWrapper.get();
    }

    public Cache get(String cacheName) {
        return ehCacheCacheManager.getCache(cacheName);
    }

    public void remove(String cacheName, Object key) {
        Cache cache = ehCacheCacheManager.getCache(cacheName);
        cache.evict(key);
    }

}
