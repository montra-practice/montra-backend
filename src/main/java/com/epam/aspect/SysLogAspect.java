package com.epam.aspect;

import com.alibaba.fastjson.JSONObject;
import com.epam.dao.SysLogRepository;
import com.epam.data.SysLog;
import com.epam.utils.ContextUtil;
import com.epam.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description: SysLogAspect
 * @author: taoz
 * @date: 2022/7/13 17:30
 */
@Aspect
@Component
public class SysLogAspect {
    @Resource
    private SysLogRepository sysLogRepository;

    @Pointcut("@annotation(sysLog)")
    public void pointCut(com.epam.annotation.SysLog sysLog) {
    }

    @Around("pointCut(sysLog)")
    public Object around(ProceedingJoinPoint point, com.epam.annotation.SysLog sysLog) throws Throwable {

        long beginTime = System.currentTimeMillis();

        //执行方法
        Object result = point.proceed();

        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time, result);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint point, long time, Object result) {
        //获取方法信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //日志实体类
        SysLog sysLogEntity = new SysLog();
        //获取注解信息
        com.epam.annotation.SysLog sysLog = method.getAnnotation(com.epam.annotation.SysLog.class);
        if (sysLog != null) {
            sysLogEntity.setOperation(sysLog.value());
        }
        //添加请求方法
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogEntity.setMethod(className + "." + methodName + "()");

        //添加请求参数
        Object[] args = point.getArgs();
        String params = JSONObject.toJSONString(args);
        sysLogEntity.setParams(params);

        //添加真实IP
        sysLogEntity.setIpAddress(IpUtil.getIpAddress());

        //其他信息
        sysLogEntity.setOperateTime(time);
        sysLogEntity.setCreateDate(new Date());
        sysLogEntity.setUserName(ContextUtil.getUsername());
        sysLogRepository.save(sysLogEntity);
    }

}
