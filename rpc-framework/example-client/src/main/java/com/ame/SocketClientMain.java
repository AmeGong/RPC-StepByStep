package com.ame;

import com.ame.entity.RpcServiceProperties;
import com.ame.proxy.RpcClientProxy;
import com.ame.remote.transport.RpcRequestTransport;
import com.ame.remote.transport.socket.SocketRpcClient;

/**
 * FileName: SocketClientMain
 * Author:   AmeGong
 * Date:     2020/12/30 19:30
 */
public class SocketClientMain {
    public static void main(String[] args) {
         RpcRequestTransport rpcRequestTransport = new SocketRpcClient();
         RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder()
                 .group("test1")
                 .version("version1").build();
         RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceProperties);
         HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
         String hello = helloService.hello(new Hello("111", "222"));
         System.out.println(hello);
    }
}
