package com.ame.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * FileName: RpcResponseCodeEnum
 * Author:   AmeGong
 * Date:     2021/1/4 14:48
 */
@AllArgsConstructor
@Getter
@ToString
public enum RpcResponseCodeEnum {
    SUCCESS(200, "The remote call is successfull!"),
    FAILED(500, "The remote call is failed!");

    private final int code;

    private final String message;
}
