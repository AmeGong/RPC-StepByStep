package com.ame;

import com.ame.client.proxy.RemoteServiceImpl;

/**
 * FileName: RpcConsmuer
 * Author:   AmeGong
 * Date:     2020/12/24 14:08
 */
public class RpcConsmuer {
    public static void main(String[] args) {
        HelloService helloService = RemoteServiceImpl.newRemoteProxyService(HelloService.class);
        String result = helloService.sayHello("Ame");
        System.out.println(result);
    }
}
