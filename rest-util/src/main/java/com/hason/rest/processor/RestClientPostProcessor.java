package com.hason.rest.processor;

import com.hason.rest.annotation.Rest;
import com.hason.rest.bean.RestInfo;
import com.hason.rest.handle.MyInvocationHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * 扫描所有符合条件接口类，生成代理类处理请求，并把代理类注册到spring容器中。
 *
 * BeanFactoryPostProcessor，保证IRequestHandle优先于其他任何bean注册到容器中
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@Component
public class RestClientPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientPostProcessor.class);

    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        defaultListableBeanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;

        String baseScanPackage = "com.hason.rest";
        Set<Class<?>> classes = new Reflections(baseScanPackage)
                .getTypesAnnotatedWith(Rest.class);
        classes.forEach(this::createProxyClass);
    }

    /**
     * 根据场景创建JDK动态代理或者CGlib动态代理
     */
    private void createProxyClass(Class<?> clazz) {
        LOGGER.info("Create proxy for class: " + clazz);

        // Rest 服务器相关信息
        final RestInfo restInfo = extractRestInfo(clazz);

        InvocationHandler handler = new MyInvocationHandler(restInfo, defaultListableBeanFactory);

        Object proxy = Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class<?>[] {clazz},
                handler);
        // 注册代理类
        registerBean(clazz.getName(), proxy);
    }

    /**
     * 把 Bean 注册到 Spring 容器
     */
    private void registerBean(String name, Object obj) {
        defaultListableBeanFactory.registerSingleton(name, obj);
    }

    /**
     * 从类中（目标类）获取服务器信息
     */
    private RestInfo extractRestInfo(Class<?> clazz) {
        Rest rest = clazz.getAnnotation(Rest.class);

        RestInfo info = new RestInfo();
        info.setHost(rest.value());

        return info;
    }

}
