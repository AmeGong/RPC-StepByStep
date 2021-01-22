package com.ame.annotation;

import java.lang.annotation.*;

/**
 * FileName: RpcReference
 * Author:   AmeGong
 * Date:     2021/1/5 16:59
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface RpcReference {
    String version() default "";

    String group() default "";;
}
