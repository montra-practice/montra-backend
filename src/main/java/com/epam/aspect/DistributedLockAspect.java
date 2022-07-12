package com.epam.aspect;

import com.epam.annotation.RedisLock;
import com.epam.redis.RedissonUtil;
import com.epam.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @description: DistributedLockAspect
 * @author: taoz
 * @date: 2022/7/12 16:36
 */

@Aspect
@Component
@Slf4j
public class DistributedLockAspect {

    @Autowired
    private RedissonUtil redissonUtil;

    @Pointcut("@annotation(redisLock)")
    public void pointCut(RedisLock redisLock) {
    }

    @Around("pointCut(redisLock)")
    public Object around(ProceedingJoinPoint pjp, RedisLock redisLock) throws Throwable {
        String key = redisLock.value();
        if (StringUtils.isEmpty(key)) {
            Object[] args = pjp.getArgs();
            key = Arrays.toString(args);
        }
        if (!redissonUtil.tryLock(key, redisLock.waitTime(), redisLock.leaseTime(), redisLock.unit())) {
            log.debug("get lock failed : " + key);
            return Result.fail("Action failed, please try again later");
        }

        //得到锁,执行方法，释放锁
        log.debug("get lock success : " + key);
        try {
            return pjp.proceed();
        } catch (Exception e) {
            log.error("execute locked method occured an exception", e);
        } finally {
            redissonUtil.unlock(key);
        }
        return null;
    }
}
