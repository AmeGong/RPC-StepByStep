package com.ame.remote.handler.spring;

import com.ame.entity.RpcServiceProperties;
import com.ame.provider.ServiceProvider;
import com.ame.remote.dto.RpcRequest;
import com.ame.remote.handler.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * FileName: SpringRpcRequestHandler
 * Author:   AmeGong
 * Date:     2021/1/12 10:28
 */
@Component
@Slf4j
public class SpringRpcRequestHandler implements RequestHandler {
    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public Object handle(RpcRequest rpcRequest) {
        RpcServiceProperties rpcServiceProperties = rpcRequest.toRpcProperties();
        Object service = serviceProvider.getService(rpcServiceProperties);
        return invokeMethod(service, rpcRequest);

    }

    private Object invokeMethod(Object service, RpcRequest rpcRequest) {
        Class<?> serviceClass = service.getClass();
        Object result = null;
        try {
            Method method = serviceClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
