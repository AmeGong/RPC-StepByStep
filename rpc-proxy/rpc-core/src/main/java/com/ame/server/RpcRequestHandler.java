package com.ame.server;

import com.ame.RpcContext;
import com.ame.exception.RpcException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * FileName: RpcRequestHandler
 * Author:   AmeGong
 * Date:     2020/12/24 14:42
 */
public class RpcRequestHandler implements Runnable {
    private Socket socket;

    public RpcRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcContext rpcContext = (RpcContext)inputStream.readObject();

            Class clazz = RegisterServicesCenter.getService(rpcContext.getServiceName());
            if(clazz == null) {
                throw new RpcException("Can not find Class: "+rpcContext.getServiceName());
            }

            Method method = clazz.getMethod(rpcContext.getMethodName(), rpcContext.getParameterTypes());
            if (method == null) {
                throw new RpcException("Can not find method: "+rpcContext.getMethodName());
            }

            Object result = method.invoke(clazz.getConstructor().newInstance(), rpcContext.getArguments());
            outputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
