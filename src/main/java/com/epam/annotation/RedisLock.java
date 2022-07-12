package com.epam.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: RedisLock
 * @author: tzoz
 * @date: 2022/7/12 16:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /**
     * 锁的资源，redis的key
     */
    String value() default "default";

    /**
     * 持锁时间,单位毫秒
     */
    long keepMills() default 30000;

    /**
     * 等待时间
     */
    int waitTime() default 0;

    /**
     * 释放锁时间
     */
    int leaseTime() default 30;

    /**
     * 时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
