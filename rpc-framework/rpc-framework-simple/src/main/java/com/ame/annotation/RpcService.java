package com.ame.annotation;

import java.lang.annotation.*;

/**
 * FileName: RpcService
 * Author:   AmeGong
 * Date:     2021/1/4 11:12
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {
    String version() default "";
    String group() default "";
}
