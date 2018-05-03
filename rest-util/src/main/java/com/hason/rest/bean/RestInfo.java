package com.hason.rest.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 服务器信息，目前只有 host
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
public class RestInfo {

    /** 主机名 */
    private String host;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("host", host)
                .toString();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
