package com.flame.flameoss.global.web.dto;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - RespDTO.class
 * @Description: // Http响应数据传输对象
 * @Create: DerekWu on 2018/2/10 18:02
 * @Version: V1.0
 */
public class RespDTO {

    /** 返回码 0=成功，其他对应其他错误码 */
    private int code;

    /** 错误的时候才会有错误消息 */
    private String errMsg;

    /** 成功的时候返回的数据对象 */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
