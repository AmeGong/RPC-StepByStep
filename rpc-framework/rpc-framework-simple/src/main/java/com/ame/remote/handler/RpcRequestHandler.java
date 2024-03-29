package com.ame.remote.handler;

import com.ame.provider.ServiceProvider;
import com.ame.remote.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * FileName: RpcRequestHandler
 * Author:   AmeGong
 * Date:     2021/1/4 14:37
 */
@Slf4j
@Component
public class RpcRequestHandler implements RequestHandler {
    @Autowired
    private  ServiceProvider serviceProvider;

    public RpcRequestHandler() {
//        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    @Override
    public Object handle(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.toRpcProperties());
        return invokeTargetMethod(rpcRequest, service);
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result = null;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
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
