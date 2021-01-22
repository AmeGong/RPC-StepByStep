package com.ame.proxy;

import com.ame.entity.RpcServiceProperties;
import com.ame.remote.transport.RpcRequestTransport;
import com.ame.remote.dto.RpcRequest;
import com.ame.remote.dto.RpcResponse;
import com.ame.remote.transport.socket.SocketRpcClient;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * FileName: RpcClientProxy
 * Author:   AmeGong
 * Date:     2020/12/30 19:38
 */
@Slf4j
public class RpcClientProxy implements InvocationHandler {

    private RpcServiceProperties rpcServiceProperties;
    private RpcRequestTransport rpcRequestTransport;

    public RpcClientProxy(RpcRequestTransport rpcRequestTransport, RpcServiceProperties rpcServiceProperties) {
        this.rpcRequestTransport = rpcRequestTransport;
        this.rpcServiceProperties = rpcServiceProperties;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoke method: [{}]", method.getName());
        RpcRequest rpcRequest = RpcRequest.builder().methodName(method.getName())
                .parameters(args)
                .interfaceName(method.getDeclaringClass().getName())
                .paramTypes(method.getParameterTypes())
                .requestId(UUID.randomUUID().toString())
                .group(rpcServiceProperties.getGroup())
                .version(rpcServiceProperties.getVersion()).build();
        RpcResponse<Object> rpcResponse = null;
        if (rpcRequestTransport instanceof SocketRpcClient) {
            rpcResponse = (RpcResponse<Object>) rpcRequestTransport.sendRequest(rpcRequest);
        }

        return rpcResponse.getData();
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }
}
