package com.ame;

import com.ame.entity.RpcServiceProperties;
import com.ame.impl.HelloServiceImpl;
import com.ame.remote.transport.socket.SocketRpcServer;

/**
 * FileName: SocketServerMain
 * Author:   AmeGong
 * Date:     2021/1/4 11:08
 */
public class SocketServerMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group("test1").version("version1").build();
        socketRpcServer.registerService(helloService, rpcServiceProperties);
        socketRpcServer.start();
    }
}
