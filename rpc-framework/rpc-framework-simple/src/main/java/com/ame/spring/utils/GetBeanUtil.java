package com.ame.spring.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * FileName: GetBeanUtil
 * Author:   AmeGong
 * Date:     2021/1/12 11:25
 */
@Component
public class GetBeanUtil implements ApplicationContextAware {
    protected static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> c) {
        return context.getBean(c);
    }
}
