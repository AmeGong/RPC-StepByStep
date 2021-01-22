package com.ame;

import com.ame.annotation.RpcScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * FileName: SpringSocketClientMain
 * Author:   AmeGong
 * Date:     2021/1/5 16:47
 */
@RpcScan(basePackage = {"com.ame"})
public class SpringSocketClientMain {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringSocketClientMain.class);
        HelloController helloController = (HelloController) applicationContext.getBean("helloController");
        helloController.test();
        AddController addController = (AddController) applicationContext.getBean("addController");
        addController.test();
    }
}
