package com.ame.providers;

import com.ame.services.impl.CalculatorImpl;
import com.ame.request.Request;
import com.ame.services.Calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * FileName: Provider
 * Author:   AmeGong
 * Date:     2020/12/23 16:07
 */
public class Provider {
    private int PORT = 9090;
    private Calculator calculator = new CalculatorImpl();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Provider().run();
    }

    private void run() throws IOException, ClassNotFoundException {
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while(true) {
                try (Socket socket = listener.accept()){
                    System.out.println("Recieve connection from sockect: "+socket.getInetAddress()+":"+socket.getPort());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    Object recv = inputStream.readObject();

                    int result = 0;
                    if (recv instanceof Request) {
                        Request request = (Request) recv;
                        if ("add".equals(request.getMethod())) {
                            result = calculator.add(request.getA(), request.getB());
                        }
                    }

                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(Integer.valueOf(result));
                }
            }
        }


    }
}
