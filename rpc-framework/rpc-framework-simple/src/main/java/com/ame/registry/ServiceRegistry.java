package com.ame.registry;

import com.ame.extension.SPI;

import java.net.InetSocketAddress;

/**
 * FileName: ServiceRegistry
 * Author:   AmeGong
 * Date:     2021/1/4 15:28
 */
@SPI
public interface ServiceRegistry {
    void registerService(String toRpcServiceName, InetSocketAddress inetSocketAddress);
}
