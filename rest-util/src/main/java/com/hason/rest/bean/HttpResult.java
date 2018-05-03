package com.hason.rest.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * HTTP 返回结果
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
public class HttpResult<T> {

    public static final int OK_CODE = 200;
    public static final String OK_MSG = "success";

    /** 状态码 */
    private int code = OK_CODE;

    /** 提示消息 */
    private String msg = OK_MSG;

    /** 数据体 */
    private T data;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("msg", msg)
                .append("data", data)
                .toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
