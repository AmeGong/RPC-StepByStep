package com.ame.providers;

import java.io.IOException;

/**
 * FileName: App
 * Author:   AmeGong
 * Date:     2020/12/23 20:47
 */
public class ProviderApp {
    public static void main(String[] args) throws IOException {
        Provider provider = new Provider(9090, 10);
        provider.start();
    }
}
