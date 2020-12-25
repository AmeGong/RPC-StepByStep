package com.ame.server;

import com.ame.exception.RpcException;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileName: RegisterServicesCenter
 * Author:   AmeGong
 * Date:     2020/12/24 14:50
 */
public class RegisterServicesCenter {
    private static ConcurrentHashMap<String, Class> registerServices = new ConcurrentHashMap<>();

    public static void setRegisterServices(ConcurrentHashMap<String, Class> registerServices) {
        RegisterServicesCenter.registerServices = registerServices;
    }

    public static ConcurrentHashMap<String, Class> getRegisterServices() {
        return registerServices;
    }

    public static Class<?> getService(String serviceName) {
        return registerServices.get(serviceName);
    }

    public static void register(String serviceName, Class clazz) {
        registerServices.put(serviceName, clazz);
    }

    public static void register(Class service, Class clazz) {
        if (service.isInterface()) {
            registerServices.put(service.getName(), clazz);
        } else {
            try {
                throw new RpcException("The service must be interface!");
            } catch (RpcException e) {
                e.printStackTrace();
            }
        }
    }
}
