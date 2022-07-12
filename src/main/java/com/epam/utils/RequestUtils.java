package com.epam.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: RequestUtils
 * @author: taoz
 * @date: 2022/7/12 16:07
 */
public class RequestUtils {
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert ra != null;
        return ra.getRequest();
    }
}