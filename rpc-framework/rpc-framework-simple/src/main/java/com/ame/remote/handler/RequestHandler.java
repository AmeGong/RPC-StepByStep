package com.ame.remote.handler;

import com.ame.remote.dto.RpcRequest;

/**
 * FileName: RequestHandler
 * Author:   AmeGong
 * Date:     2021/1/12 10:40
 */
public interface RequestHandler {
    public Object handle(RpcRequest rpcRequest);
}
