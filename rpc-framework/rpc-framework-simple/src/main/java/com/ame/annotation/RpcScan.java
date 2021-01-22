package com.ame.annotation;

import com.ame.spring.CustomScannerRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * FileName: RpcScan
 * Author:   AmeGong
 * Date:     2021/1/5 15:11
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomScannerRegister.class)
public @interface RpcScan {
    String[] basePackage();
}
