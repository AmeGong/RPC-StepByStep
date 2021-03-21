package com.ame.loadbalance.loadbalancer;

import com.ame.loadbalance.AbstractLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * FileName: RandomLoadBalance
 * Author:   AmeGong
 * Date:     2021/1/4 10:48
 */
@Slf4j
@Component
public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> serviceAddresses, String rpcServiceName) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
