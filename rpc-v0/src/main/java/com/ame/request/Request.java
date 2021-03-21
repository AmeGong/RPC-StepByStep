package com.ame.request;

import java.io.Serializable;

/**
 * FileName: request
 * Author:   AmeGong
 * Date:     2020/12/23 16:22
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 7503710091945320739L; 
    private String method;        // the name of the invoked method
    private int a;               // the first parameter
    private int b;              // the first parameter

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "CalculateRpcRequest{" +
                "method='" + method + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
