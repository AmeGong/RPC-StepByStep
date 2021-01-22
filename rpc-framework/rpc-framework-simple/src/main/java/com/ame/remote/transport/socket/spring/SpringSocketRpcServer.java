package com.ame.remote.transport.socket.spring;

import com.ame.entity.RpcServiceProperties;
import com.ame.provider.ServiceProvider;
import com.ame.utils.concurrent.threadpool.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


import static com.ame.utils.Consistant.PORT;

/**
 * FileName: SpringSocketRpcServer
 * Author:   AmeGong
 * Date:     2021/1/12 10:15
 */
@Component
@Slf4j
public class SpringSocketRpcServer {
    private ExecutorService pool;

    @Autowired
    private ServiceProvider serviceProvider;

    public SpringSocketRpcServer() {
        pool = ThreadPoolFactoryUtils.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
    }


    public void register(Object service, RpcServiceProperties rpcServiceProperties) {
        serviceProvider.publishService(service, rpcServiceProperties);
    }

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket()) {
            String host = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(host, PORT));

            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                pool.execute(new SpringSocketRpcRequestHandlerRunnable(socket));
            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
