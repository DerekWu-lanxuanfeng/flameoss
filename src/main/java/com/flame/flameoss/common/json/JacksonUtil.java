package com.flame.flameoss.common.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - JacksonUtil.class
 * @Description: // Jsckson 工具类
 * @Create: DerekWu on 2018/2/10 18:46
 * @Version: V1.0
 */
public class JacksonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String encode(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOG.error("jackson encode error:", e);
        }
        return null;
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            LOG.error("jackson decode(String, Class<T>) error: ", e);
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            LOG.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }

}
