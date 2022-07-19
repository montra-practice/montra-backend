package com.epam.annotation;

import org.redisson.api.RateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 接口限流
 *
 * @Author taoz
 * @Date 2022/7/15 16:08
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APILimit {

    /**
     * 要获取的令牌数
     */
    int permits() default 5;

    /**
     * 产生令牌的间隔
     */
    int rateInterval() default 1;

    /**
     * 等待时间，在没有令牌可获取的情况下
     */
    int waitingTime() default 0;

    /**
     * RateType
     */
    RateType rateType() default RateType.OVERALL;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
