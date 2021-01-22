package com.ame.remote.dto;

import com.ame.entity.RpcServiceProperties;
import lombok.*;

import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * FileName: RpcRequest
 * Author:   AmeGong
 * Date:     2020/12/30 19:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public RpcServiceProperties toRpcProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }

}