package com.ame;

import com.ame.annotation.RpcReference;
import org.springframework.stereotype.Component;

/**
 * FileName: AddController
 * Author:   AmeGong
 * Date:     2021/1/19 20:17
 */
@Component
public class AddController {
    @RpcReference(version = "test1", group = "group1")
    private AddService addService;

    public void test(){
        for (int i = 0; i < 10; i++) {
            System.out.println(addService.add(1, 2));
        }
    }
}
