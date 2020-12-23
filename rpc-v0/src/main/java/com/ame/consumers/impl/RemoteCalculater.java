package com.ame.consumers.impl;

import com.ame.request.Request;
import com.ame.services.Calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RemoteCalculater
 * Author:   AmeGong
 * Date:     2020/12/23 16:10
 */
public class RemoteCalculater implements Calculator {
    private int port = 9090;

    @Override
    public int add(int a, int b) {
        Object response = null;
        try {
            // 优化点：可以用反射获得方法的全限定名;
            List<String> addressList = lookupProviders("Calculator.add");
            String host = chooseProvider(addressList);
            try(Socket socket = new Socket(host, port)) {
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                Request request = generateRequest(a, b);

                // 通过socket将调用的方法，参数发送给服务器
                outputStream.writeObject(request);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                response = inputStream.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response instanceof Integer) {
            return (Integer) response;
        } else {
            throw new InternalError();
        }
    }

    private Request generateRequest(int a, int b) {
        Request request = new Request();
        request.setA(a);
        request.setB(b);
        request.setMethod("add");
        return request;
    }

    private String chooseProvider(List<String> addressList) {
        if (addressList == null || addressList.size() == 0){
            throw new IllegalArgumentException();
        }

        return addressList.get(0);
    }

    private List<String> lookupProviders(String serviceName) {
        List<String> strings = new ArrayList<>();
        strings.add("127.0.0.1");
        return strings;
    }
}
