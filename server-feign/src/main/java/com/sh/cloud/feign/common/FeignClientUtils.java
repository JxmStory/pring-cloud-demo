package com.sh.cloud.feign.common;

import feign.hystrix.FallbackFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FeignClientUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static final Map<String, Object> BEAN_CACHE = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (FeignClientUtils.applicationContext == null) {
            FeignClientUtils.applicationContext = applicationContext;
        }
    }

    public static <T> T build(String serverName,
                              String path,
                              Class<T> targetClass,
                              Class<? extends FallbackFactory<? extends T>> fallbackFactory) {
        return buildClient(serverName, path, targetClass, fallbackFactory);
    }

    @SuppressWarnings("unchecked")
    private static <T> T buildClient(String serverName,
                                     String path, Class<T> targetClass,
                                     Class<? extends FallbackFactory<? extends T>> fallbackFactory) {
        T t = (T) BEAN_CACHE.get(serverName);
        if (Objects.isNull(t)) {
            FeignClientBuilder feignClientBuilder = new FeignClientBuilder(applicationContext);
            FeignClientBuilder.Builder<T> builder = feignClientBuilder.forType(targetClass, serverName);
            builder.path(path);
            builder.fallbackFactory(fallbackFactory);
            t = builder.build();
            BEAN_CACHE.put(serverName, t);
        }
        return t;
    }
}