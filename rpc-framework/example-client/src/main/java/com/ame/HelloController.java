package com.ame;

import com.ame.annotation.RpcReference;
import com.ame.annotation.RpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * FileName: HelloController
 * Author:   AmeGong
 * Date:     2021/1/5 16:56
 */
@Component
public class HelloController {

    @RpcReference(version = "version1", group = "test1")
    private HelloService helloService;

    public void test() throws InterruptedException {
        String hello = this.helloService.hello(new Hello("111", "222"));
        assert "Hello description is 222".equals(hello);

//        Thread.sleep(12000);
        for (int i = 0; i < 10; i++) {
            System.out.println(helloService.hello(new Hello("111", "222")));
        }
    }
}
