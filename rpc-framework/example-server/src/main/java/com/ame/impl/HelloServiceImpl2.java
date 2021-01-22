package com.ame.impl;

import com.ame.Hello;
import com.ame.HelloService;
import com.ame.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName: HelloServiceImpl2
 * Author:   AmeGong
 * Date:     2021/1/5 17:02
 */
@Slf4j
@RpcService(group = "test1", version = "version1")
public class HelloServiceImpl2 implements HelloService {
    static {
        System.out.println("HelloServiceImpl被创建。");
    }
    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl收到： {}.", hello.getMessage());
        String result = "Hello description is "+hello.getDescription();
        log.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}
