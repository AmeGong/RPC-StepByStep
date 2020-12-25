package com.ame.client.proxy;

import java.lang.reflect.Proxy;

/**
 * FileName: RemoteServiceImpl
 * Author:   AmeGong
 * Date:     2020/12/24 11:18
 */
public class RemoteServiceImpl<T> {
    public static <T> T newRemoteProxyService(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(),new Class[] {service}, new ProxyHandler(service));
    }
}
