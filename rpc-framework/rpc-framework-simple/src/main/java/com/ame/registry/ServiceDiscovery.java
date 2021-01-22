package com.ame.registry;

import com.ame.extension.SPI;

import java.net.InetSocketAddress;

/**
 * FileName: ServiceDiscovery
 * Author:   AmeGong
 * Date:     2020/12/30 20:16
 */
@SPI
public interface ServiceDiscovery {

    InetSocketAddress lookupService(String serviceName);
}
