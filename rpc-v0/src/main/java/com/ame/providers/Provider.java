package com.ame.providers;


import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FileName: ProviderApp
 * Author:   AmeGong
 * Date:     2020/12/23 19:33
 */
public class Provider {
    private final ExecutorService pool;
    private final Listener listener;

    public Provider(int port, int poolSize) throws IOException {
        pool = Executors.newFixedThreadPool(10);
        listener = new Listener(9090);
    }

    public void start() throws IOException {
        try {
            while(true) {
                // 这里不能使用try with resource语句，因为关闭socket需要等待consumer方，
                // 多线程时，execute用另一个个线程执行，所以用try with resource会立马关闭socket
                pool.execute(new Handler(listener.start()));
            }
        } finally {
            pool.shutdown();
        }
    }
}
