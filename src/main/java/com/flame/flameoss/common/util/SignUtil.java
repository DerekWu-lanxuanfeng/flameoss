package com.flame.flameoss.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author yuLiang
 */
public class SignUtil {
    // 创建签名
    public static String getMD5Sign(String wxPartnerKey, Map map) {
        StringBuffer sb = new StringBuffer();
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + v + "&");
        }
        String params = sb.substring(0, sb.lastIndexOf("&"));

        String signTemp = params + "&key=" + wxPartnerKey;

        String appsign = MD5Util.MD5Encode(signTemp, "UTF-8").toUpperCase();
        return appsign;
    }

    // 创建签名
    public static String getMD5Sign(String wxPartnerKey, Object bean) {
        SortedMap paraMap = new TreeMap();
        StringBuffer sb = new StringBuffer();
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();

        // 先对参数排序
        for (Field field : fields) {
            field.setAccessible(true); // 设置些属性是可以访问的
            String k = field.getName();
            String v = null;
            try {
                Object object = field.get(bean);
                if (object == null) {
                    continue;
                } else if (object instanceof String) {
                    v = (String) object;
                } else {
                    v = object + "";
                }

            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            paraMap.put(k, v);
        }

        // 拼接
        for (Object para : paraMap.keySet()) {
            if ("packages".equals(para)) {
                sb.append("package=" + paraMap.get(para) + "&");
            } else {
                sb.append(para + "=" + paraMap.get(para) + "&");
            }
        }

        String params = sb.substring(0, sb.lastIndexOf("&"));

        String signTemp = params + "&key=" + wxPartnerKey;

        String appsign = MD5Util.MD5Encode(signTemp, "UTF-8").toUpperCase();
        return appsign;
    }

    // 验证签名
    public static boolean checkSign(String wxPartnerKey, Object bean) {
        String sign = null;
        String validSign = null;
        try {
            Field field = bean.getClass().getDeclaredField("sign");
            field.setAccessible(true); // 设置些属性是可以访问的

            /** 微信端返回的合法签名 */
            validSign = (String) field.get(bean);
            field.set(bean, null);// 签名置空

            /** 验证的签名 */
            sign = getMD5Sign(wxPartnerKey, bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return validSign.equals(sign);
    }

    /**
     * sha1 签名
     *
     * @param decript
     * @return
     */
    public static String signSHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写 true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "UTF-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }


}
