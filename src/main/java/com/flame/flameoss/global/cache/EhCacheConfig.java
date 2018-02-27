package com.flame.flameoss.global.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EhCacheConfig {

    @Bean(name="ehcacheManager")
    public EhCacheCacheManager cacheManager() {
        return new EhCacheCacheManager(CacheManager.getInstance());
    }

}
