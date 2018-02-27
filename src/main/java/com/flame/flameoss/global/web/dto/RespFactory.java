package com.flame.flameoss.global.web.dto;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - RespFactory.class
 * @Description: // 响应工厂类
 * @Create: DerekWu on 2018/2/10 18:10
 * @Version: V1.0
 */
public class RespFactory {

    /**
     * 构建数据，这个时候code=0
     * @param data
     * @return
     */
    public static RespDTO build(Object data) {
        RespDTO respDTO = new RespDTO();
        respDTO.setCode(0);
        respDTO.setData(data);
        return respDTO;
    }

    /**
     * 构建错误消息
     * @param code
     * @param errMsg
     * @return
     */
    public static RespDTO buildErrMsg(int code, String errMsg) {
        RespDTO respDTO = new RespDTO();
        respDTO.setCode(code);
        respDTO.setErrMsg(errMsg);
        return respDTO;
    }

}
