package com.ame.service.impl;

import com.ame.HelloService;

/**
 * FileName: HelloServiceImpl
 * Author:   AmeGong
 * Date:     2020/12/24 15:11
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String word) {
        return "Hello, "+word;
    }
}
