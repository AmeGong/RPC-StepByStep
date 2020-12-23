package com.ame.consumers;

import com.ame.consumers.impl.RemoteCalculater;
import com.ame.request.Request;
import com.ame.services.Calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FileName: ConcurrentConsumer
 * Author:   AmeGong
 * Date:     2020/12/23 20:19
 */
public class Consumer implements Runnable{
    private int a, b;
    Calculator remoteCalculater = new RemoteCalculater();
    public Consumer(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        int result = remoteCalculater.add(a, b);
        System.out.println(result);
    }
}

