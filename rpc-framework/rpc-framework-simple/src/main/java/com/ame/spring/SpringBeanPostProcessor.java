package com.ame.spring;

import com.ame.annotation.RpcReference;
import com.ame.annotation.RpcService;
import com.ame.entity.RpcServiceProperties;
import com.ame.extension.ExtensionLoader;
import com.ame.factory.SingletonFactory;
import com.ame.provider.ServiceProvider;
import com.ame.provider.impl.ServiceProviderImpl;
import com.ame.proxy.RpcClientProxy;
import com.ame.remote.transport.RpcRequestTransport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * FileName: SpringBeanPostProcessor
 * Author:   AmeGong
 * Date:     2021/1/5 17:11
 */
@Component
@Slf4j
public class SpringBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private ServiceProvider serverProvider;
    private final RpcRequestTransport rpcClient;

    public SpringBeanPostProcessor() {
//        this.serverProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
        this.rpcClient = ExtensionLoader.getExtensionLoader(RpcRequestTransport.class).getExtension("socket");
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            log.info("[{}] is annotated with  [{}]", bean.getClass().getName(), RpcService.class.getCanonicalName());
            // get RpcService annotation
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            // build RpcServiceProperties
            RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group(rpcService.group()).version(rpcService.version()).build();
            serverProvider.publishService(bean, rpcServiceProperties);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference reference = declaredField.getAnnotation(RpcReference.class);
            if (reference != null) {
                RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group(reference.group()).version(reference.version()).build();
                RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient, rpcServiceProperties);
                Object clientProxy = rpcClientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
