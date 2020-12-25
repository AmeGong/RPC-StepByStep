package com.ame.server;

/**
 * FileName: RpcServer
 * Author:   AmeGong
 * Date:     2020/12/24 14:13
 */
public interface RpcServer {

    void start();

    void stop();

    void register(String className, Class clazz) throws Exception;

    boolean isAlive();
}
