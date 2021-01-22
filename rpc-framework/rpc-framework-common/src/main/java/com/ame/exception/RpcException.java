package com.ame.exception;

import com.ame.enums.RpcErrorMessageEnum;

/**
 * FileName: RpcException
 * Author:   AmeGong
 * Date:     2021/1/4 14:55
 */
public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum, String detail) {
        super(rpcErrorMessageEnum.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum) {}
}
