package com.hason.rest.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedHashMap;

/**
 * 请求信息，如：地址、返回类型、请求参数等
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
public class RequestInfo {

    /** 请求地址 */
    private String url;

    /** 返回类型 */
    private Class<?> returnType;

    /** 请求参数 */
    private LinkedHashMap<String, String> params;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("url", url)
                .append("returnType", returnType)
                .append("params", params)
                .toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public LinkedHashMap<String, String> getParams() {
        return params;
    }

    public void setParams(LinkedHashMap<String, String> params) {
        this.params = params;
    }
}
