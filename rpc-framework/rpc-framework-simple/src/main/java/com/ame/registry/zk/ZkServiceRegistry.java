package com.ame.registry.zk;

import com.ame.registry.ServiceRegistry;
import com.ame.registry.zk.util.CuratorUtils;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * FileName: ZkServiceRegistry
 * Author:   AmeGong
 * Date:     2021/1/4 15:32
 */
@Component
public class ZkServiceRegistry implements ServiceRegistry {
    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH+"/"+ rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createPersistentNode(zkClient, servicePath);
    }
}
