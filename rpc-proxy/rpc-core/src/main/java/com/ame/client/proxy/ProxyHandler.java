package com.ame.client.proxy;

import com.ame.RpcContext;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * FileName: ProxyHandler
 * Author:   AmeGong
 * Date:     2020/12/24 10:22
 */
public class ProxyHandler implements InvocationHandler {
    private long timeout = 1000;
    private Class<?> service;
    private ExecutorService executor;
    private InetSocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 8989);

    public ProxyHandler(Class<?> service) {
        this.service = service;
        executor = Executors.newFixedThreadPool(1);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcContext rpcContext = new RpcContext();
        rpcContext.setServiceName(service.getName());
        rpcContext.setMethodName(method.getName());
        rpcContext.setArguments(args);
        rpcContext.setParameterTypes(method.getParameterTypes());

        rpcContext.setRemoteAddress(remoteAddress);
        return request(rpcContext);
    }

    private Object request(RpcContext rpcContext) {
        Object result = null;
        Future future = executor.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return doRequest(rpcContext);
                    }
                }
        );
        try {
            result = future.get(timeout, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("The request is timeout and giveup...");
            future.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return result;
    }

    private Object doRequest(RpcContext rpcContext) {
        Object result = null;
        try(Socket socket = new Socket(remoteAddress.getAddress(), remoteAddress.getPort());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){

            outputStream.writeObject(rpcContext);

            // 通知服务端消息发送完毕，可以接收
            socket.shutdownOutput();
            result = inputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
