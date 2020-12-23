package com.ame.providers;

import com.ame.request.Request;
import com.ame.services.Calculator;
import com.ame.services.impl.CalculatorImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * FileName: Handler
 * Author:   AmeGong
 * Date:     2020/12/23 19:29
 */
public class Handler implements Runnable {
    private Socket socket;
    private Calculator calculator = new CalculatorImpl();

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
