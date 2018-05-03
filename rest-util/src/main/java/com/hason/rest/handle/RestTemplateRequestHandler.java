package com.hason.rest.handle;

import com.hason.rest.bean.RequestInfo;
import com.hason.rest.bean.RestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 基于 RestTemplate 的网络请求处理器，需要在 Spring 容器中注册 RestTemplate
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@Component
public class RestTemplateRequestHandler implements RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateRequestHandler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object handle(RestInfo info, RequestInfo request) {
        LOGGER.info("Handle request, RestInfo=" + info);
        LOGGER.info("Handle request, RequestInfo=" + request);

        String url = extractUrl(info, request);
        LOGGER.info("Handle request url: " + url);

        //TODO 目前只写了get请求，需要支持post等在这里增加
        //TODO 需要在这里增加异常处理，如登录失败，链接不上

        Object result = restTemplate.getForObject(url, request.getReturnType());
        LOGGER.info("Handle request, return body: " + result);

        return result;
    }

    /**
     * 生成真实的url
     */
    private String extractUrl(RestInfo info, RequestInfo request) {
        String url = info.getHost() + request.getUrl();

        if (request.getParams() == null || request.getParams().isEmpty()) {
            return url;
        }

        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            params.append(entry.getKey()).append('=').append(entry.getValue())
                    .append('&');
        }

        params.setLength(params.length() - 1);
        return url + "?" + params.toString();
    }

}
