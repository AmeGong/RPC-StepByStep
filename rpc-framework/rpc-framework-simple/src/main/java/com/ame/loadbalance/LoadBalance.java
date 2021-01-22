package com.ame.loadbalance;

import com.ame.extension.SPI;

import java.util.List;

/**
 * FileName: LoadBalance
 * Author:   AmeGong
 * Date:     2021/1/4 9:50
 */
@SPI
public interface LoadBalance {

    String selectServiceAddress(List<String> serviceAddresses, String rpcServiceName);
}
