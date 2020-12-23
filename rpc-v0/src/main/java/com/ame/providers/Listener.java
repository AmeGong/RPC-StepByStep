package com.ame.providers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * FileName: ConsumerListener
 * Author:   AmeGong
 * Date:     2020/12/23 19:22
 */
public class Listener{
    private final ServerSocket serverSocket;

    public Listener(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public Socket start() throws IOException {
        return serverSocket.accept();
    }
}
