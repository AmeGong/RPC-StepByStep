package com.ame;

import lombok.Data;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * FileName: RpcContext
 * Author:   AmeGong
 * Date:     2020/12/24 10:40
 */
@Data
public class RpcContext implements Serializable {
    private String serviceName;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] arguments;
    private InetSocketAddress localAddress;
    private InetSocketAddress remoteAddress;
    private long timeout = 10000;

    @Override
    public String toString() {
        return "RpcContext{" +
            "serviceName='" + serviceName + '\'' +
            ", methodName='" + methodName + '\'' +
            ", parameterTypes=" + Arrays.toString(parameterTypes) +
            ", arguments=" + Arrays.toString(arguments) +
            ", localAddress=" + localAddress +
            ", remoteAddress=" + remoteAddress +
            ", timeout=" + timeout +
            '}';
    }
}
