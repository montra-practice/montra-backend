package com.epam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 防止网页重复提交
 * @author: tzoz
 * @date: 2022/7/12 16:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {
    /**
     * 设置请求锁定时间
     *
     * @return
     */
    int lockTime() default 10;
}
