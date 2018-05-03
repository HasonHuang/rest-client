package com.hason.rest.handle;


import com.hason.rest.bean.RequestInfo;
import com.hason.rest.bean.RestInfo;

/**
 * 网络请求处理器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
public interface RequestHandler {

    /**
     * 处理网络请求
     *
     * @param info
     * @param request
     * @return
     */
    Object handle(RestInfo info, RequestInfo request);

}
