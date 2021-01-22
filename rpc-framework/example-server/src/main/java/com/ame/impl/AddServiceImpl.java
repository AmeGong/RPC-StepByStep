package com.ame.impl;

import com.ame.AddService;
import com.ame.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName: AddService
 * Author:   AmeGong
 * Date:     2021/1/19 20:06
 */
@Slf4j
@RpcService(version = "test1", group = "group1")
public class AddServiceImpl implements AddService {
    static {
        System.out.println("AddService被创建。");
    }

    @Override
    public int add(int a, int b) {
        return a+b;
    }
}
