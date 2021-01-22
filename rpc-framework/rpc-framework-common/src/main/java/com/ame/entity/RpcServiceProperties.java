package com.ame.entity;

import lombok.*;

/**
 * FileName: RpcServiceProperties
 * Author:   AmeGong
 * Date:     2020/12/30 19:43
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RpcServiceProperties {
    private String version;
    private String group;
    private String serviceName;

    public String toRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }
}
