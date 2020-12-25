package com.ame.server.impl;

import com.ame.exception.RpcException;
import com.ame.server.RegisterServicesCenter;
import com.ame.server.RpcRequestHandler;
import com.ame.server.RpcServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * FileName: RpcServerImpl
 * Author:   AmeGong
 * Date:     2020/12/24 14:28
 */
public class RpcServerImpl implements RpcServer {
    private int nThreads = 10;
    private boolean isAlive = false;
    private int port = 8989;
    private ExecutorService pool;

    private void init(){
        pool = Executors.newFixedThreadPool(nThreads);
    }

    public RpcServerImpl(int port, int nThreads) {
        this.nThreads = nThreads;
        this.port = port;
        init();
    }

    public RpcServerImpl(int port) {
        this.port = port;
        init();
    }

    @Override
    public void start() {
        isAlive = true;
        try(ServerSocket listener = new ServerSocket(port)) {
            System.out.println("The server has started...");
            while(true) {
                Socket socket = listener.accept();
                System.out.println("Recieve request from socket: "+socket.getInetAddress()+":"+socket.getPort());
                pool.submit(new RpcRequestHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        isAlive = false;
        pool.shutdown();
    }

    @Override
    public void register(String className, Class clazz) throws Exception {
        if (RegisterServicesCenter.getRegisterServices() != null) {
            RegisterServicesCenter.register(className, clazz);
        }
        else {
            throw new RpcException("The RPC server has not initialized!");
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }
}
