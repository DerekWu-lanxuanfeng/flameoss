package com.flame.flameoss.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - HttpSessionConfig.class
 * @Description: // HttpSessionConfig
 * @Create: DerekWu on 2018/2/25 18:42
 * @Version: V1.0
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "FlameOSS")
public class HttpSessionConfig {

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

//    @Bean
//    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
//        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        cookieSerializer.setCookieName("OSS_SESSION_ID");//cookies名称
//        cookieSerializer.setCookieMaxAge(1800);//过期时间(秒)
//        strategy.setCookieSerializer(cookieSerializer);
//        return strategy;
//    }

}
