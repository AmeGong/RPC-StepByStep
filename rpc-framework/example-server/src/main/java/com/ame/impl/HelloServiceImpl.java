package com.ame.impl;

import com.ame.Hello;
import com.ame.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName: HelloServiceImpl
 * Author:   AmeGong
 * Date:     2021/1/4 11:09
 */
@Slf4j
public class HelloServiceImpl implements HelloService {
    static {
        System.out.println("HelloServiceImpl被创建");
    }

    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl收到： {}.", hello.getMessage());
        String result = "Hello description is "+hello.getDescription();
        log.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}
