package com.ame.consumers;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FileName: App
 * Author:   AmeGong
 * Date:     2020/12/23 20:49
 */
public class ConsumerApp {
    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(100);
            Random random = new Random();
            for(int i = 0; i<100; i++) {
                executorService.execute(new Consumer(random.nextInt(), random.nextInt()));
            }
        } finally {
            executorService.shutdown();
        }
    }
}
