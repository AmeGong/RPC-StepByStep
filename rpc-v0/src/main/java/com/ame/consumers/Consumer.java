package com.ame.consumers;

import com.ame.consumers.impl.RemoteCalculater;
import com.ame.services.Calculator;

import java.io.IOException;

/**
 * FileName: Consumer
 * Author:   AmeGong
 * Date:     2020/12/23 16:06
 */
public class Consumer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Calculator calculoter = new RemoteCalculater();
        Integer result = calculoter.add(1,2);
        System.out.println("The result of RPC is: " + result);
    }
}
