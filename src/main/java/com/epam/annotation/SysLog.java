package com.epam.annotation;

import java.lang.annotation.*;

/**
 * @description: 日志注解
 * @author: tzoz
 * @date: 2022/7/13 17:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
