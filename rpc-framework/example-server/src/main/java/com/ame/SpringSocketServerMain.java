package com.ame;

import com.ame.annotation.RpcScan;
import com.ame.entity.RpcServiceProperties;
import com.ame.impl.HelloServiceImpl2;
import com.ame.remote.transport.socket.SocketRpcServer;
import com.ame.remote.transport.socket.spring.SpringSocketRpcServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * FileName: SpringSocketServerMain
 * Author:   AmeGong
 * Date:     2021/1/6 16:02
 */
@RpcScan(basePackage = {"com.ame"})
public class SpringSocketServerMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringSocketServerMain.class);
        SpringSocketRpcServer rpcServer = applicationContext.getBean(SpringSocketRpcServer.class);
//        HelloService helloService= new HelloServiceImpl2();
//        RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group("test1").version("version1").build();
//        rpcServer.registerService(helloService, rpcServiceProperties);
        rpcServer.start();
    }
}
