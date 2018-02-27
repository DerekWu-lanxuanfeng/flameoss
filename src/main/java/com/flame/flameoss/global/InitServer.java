package com.flame.flameoss.global;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - InitServer.class
 * @Description: // 初始化服务
 * @Create: DerekWu on 2018/2/25 20:32
 * @Version: V1.0
 */
public class InitServer {

    public static void init() {
//        resetSessionRedisTemplate();
    }

//    private static void resetSessionRedisTemplate() {
//        RedisTemplate template = (RedisTemplate)SpringContextUtils.getBean("sessionRedisTemplate");
//        StringRedisSerializer stringSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringSerializer);
////        template.setValueSerializer(stringSerializer);
//        template.setHashKeySerializer(stringSerializer);
//        template.setHashValueSerializer(stringSerializer);
//
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setDefaultSerializer(jackson2JsonRedisSerializer);
//
//        template.afterPropertiesSet();
//    }

}
