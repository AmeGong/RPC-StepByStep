package com.ame.remote.transport.socket;

import com.ame.entity.RpcServiceProperties;
import com.ame.exception.RpcException;
import com.ame.registry.ServiceDiscovery;
import com.ame.remote.dto.RpcRequest;
import com.ame.remote.transport.RpcRequestTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * FileName: SocketRpcClient
 * Author:   AmeGong
 * Date:     2020/12/30 19:47
 */
@Component
public class SocketRpcClient implements RpcRequestTransport {
    @Autowired
    private ServiceDiscovery serviceDiscovery;

    public SocketRpcClient() {
        // initialize the serviceDiscovery
//        serviceDiscovery = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("zk");
    }
    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        String rpcServiceName = RpcServiceProperties.builder().serviceName(rpcRequest.getInterfaceName())
                .group(rpcRequest.getGroup()).version(rpcRequest.getVersion()).build().toRpcServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcServiceName);
        try(Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException  e) {
            throw new RpcException("调用服务失败: ", e);
        }
    }
}