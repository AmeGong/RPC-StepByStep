package com.ame.remote.transport.socket.spring;

import com.ame.entity.RpcServiceProperties;
import com.ame.provider.ServiceProvider;
import com.ame.remote.dto.RpcRequest;
import com.ame.remote.dto.RpcResponse;
import com.ame.remote.handler.spring.SpringRpcRequestHandler;
import com.ame.spring.utils.GetBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

/**
 * FileName: SpringSocketRpcRequestHandlerRunnable
 * Author:   AmeGong
 * Date:     2021/1/12 10:31
 */
@Slf4j
public class SpringSocketRpcRequestHandlerRunnable implements Runnable {
    private Socket socket;
    private SpringRpcRequestHandler springRpcRequestHandler;

    public SpringSocketRpcRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
        this.springRpcRequestHandler = GetBeanUtil.getBean(SpringRpcRequestHandler.class);
    }

    @Override
    public void run() {
        log.info("server handle message from client by thread: [{}]", Thread.currentThread().getName());
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            Object result = this.springRpcRequestHandler.handle(rpcRequest);
            objectOutputStream.writeObject(RpcResponse.success(result, rpcRequest.getRequestId()));
            objectOutputStream.flush();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
