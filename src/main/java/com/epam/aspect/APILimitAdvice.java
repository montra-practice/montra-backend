package com.epam.aspect;

import com.epam.annotation.APILimit;
import com.epam.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 实现限流
 *
 * @Author taoz
 * @Date 2022/7/15 16:28
 **/
@Aspect
@Component
@Slf4j
public class APILimitAdvice {

    @Autowired
    private RedissonClient redissonClient;


    @Around(value = "@annotation(apiLimit)")
    public Object around(ProceedingJoinPoint joinPoint, APILimit apiLimit) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        int waitingTime = apiLimit.waitingTime();
        int permits = apiLimit.permits();
        int rateInterval = apiLimit.rateInterval();
        TimeUnit timeUnit = apiLimit.timeUnit();
        RateType rateType = apiLimit.rateType();

        // 根据类名 + 方法名作为Map的key值
        Class clazz = joinPoint.getTarget().getClass();
        String rateLimiterKey = clazz.getSimpleName() + signature.getName();

        RRateLimiter limiter = getRateLimiter(rateLimiterKey, permits, rateInterval, rateType);

        if (limiter.tryAcquire(waitingTime, timeUnit)) {//尝试获取1个令牌
            log.info("获取令牌成功");
            return joinPoint.proceed();
        } else {
            return Result.fail("服务请求达到最大限制，请求被拒绝！");
        }
    }

    private RRateLimiter getRateLimiter(String rateLimiterKey, int permits, int rateInterval, RateType rateType) {
        RRateLimiter limiter = redissonClient.getRateLimiter("rateLimiterKey");
        if (!limiter.isExists()) {
            synchronized (rateLimiterKey) {
                if (!limiter.isExists()) {
                    limiter.trySetRate(rateType, permits, rateInterval, RateIntervalUnit.SECONDS);
                    limiter.expire(30, TimeUnit.SECONDS);
                }
            }
        }
        return limiter;
    }

}

