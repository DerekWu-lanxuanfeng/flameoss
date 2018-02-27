package com.flame.flameoss.global.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flame.flameoss.global.SpringContextUtils;
import com.google.protobuf.GeneratedMessage;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - RedisTemplateFactory.class
 * @Description: // redis 模板工厂
 * @Create: DerekWu on 2018/2/25 12:41
 * @Version: V1.0
 */
public class RedisTemplateFactory {

    private static Map<Class, RedisTemplate> JACKSON_MAP = new ConcurrentHashMap<>();
    private static Map<Class, RedisTemplate> PROTOBUF_MAP = new ConcurrentHashMap<>();

    /**
     * 构建Jackson模板
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> RedisTemplate<String, T> buildJackson(Class<T> clazz) {
        synchronized (clazz) {
            RedisTemplate<String, T> template = JACKSON_MAP.get(clazz);
            if (template == null) {
                RedisConnectionFactory factory = SpringContextUtils.getBean(RedisConnectionFactory.class);
                template = new RedisTemplate<>();
                template.setConnectionFactory(factory);

                RedisSerializer<String> stringSerializer = new StringRedisSerializer();
                template.setKeySerializer(stringSerializer);
//              template.setValueSerializer(stringSerializer);
                template.setHashKeySerializer(stringSerializer);
                template.setHashValueSerializer(stringSerializer);

                Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(clazz);
                ObjectMapper om = new ObjectMapper();
                om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
                om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
                jackson2JsonRedisSerializer.setObjectMapper(om);
                template.setValueSerializer(jackson2JsonRedisSerializer);

                template.afterPropertiesSet();
                JACKSON_MAP.put(clazz, template);
            }
            return template;
        }

    }

    /**
     * 构建Protobuf模板
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends GeneratedMessage> RedisTemplate<String, T> buildProtobuf(Class<T> clazz) {
        synchronized (clazz) {
            RedisTemplate<String, T> template = PROTOBUF_MAP.get(clazz);
            if (template == null) {
                RedisConnectionFactory factory = SpringContextUtils.getBean(RedisConnectionFactory.class);
                template = new RedisTemplate<>();
                template.setConnectionFactory(factory);

                RedisSerializer<String> stringSerializer = new StringRedisSerializer();
                template.setKeySerializer(stringSerializer);
                template.setHashKeySerializer(stringSerializer);
                template.setHashValueSerializer(stringSerializer);

                ProtobufRedisSerializer protobufRedisSerializer = new ProtobufRedisSerializer(clazz);
                template.setValueSerializer(protobufRedisSerializer);

                template.afterPropertiesSet();
                PROTOBUF_MAP.put(clazz, template);
            }
            return template;
        }
    }

}
