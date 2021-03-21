package com.ame.remote.transport;

import com.ame.extension.SPI;
import com.ame.remote.dto.RpcRequest;

/**
 * FileName: RpcRequestTransport
 * Author:   AmeGong
 * Date:     2020/12/30 19:46
 */
@SPI
public interface RpcRequestTransport {
    Object sendRequest(RpcRequest rpcRequest);
}
