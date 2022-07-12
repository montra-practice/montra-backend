package com.epam.aspect;

import com.epam.annotation.NoRepeatSubmit;
import com.epam.redis.RedissonUtil;
import com.epam.utils.RequestUtils;
import com.epam.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 重复提交切面
 * @author: taoz
 * @date: 2022/7/12 16:08
 */
@Aspect
@Component
public class RepeatSubmitAspect {

    @Autowired
    private RedissonUtil redissonUtil;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around("pointCut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        int lockSeconds = noRepeatSubmit.lockTime();

        HttpServletRequest request = RequestUtils.getRequest();
        Assert.notNull(request, "request can not null");

        // 此处可以用token或者JSessionId
        String token = request.getHeader("token");
        String path = request.getServletPath();
        String sign = path + "/" + token;
        if (redissonUtil.tryLock(sign, 0, lockSeconds)) {
            return pjp.proceed();
        } else {
            return Result.fail("重复请求，请稍后再试");
        }
    }

}
