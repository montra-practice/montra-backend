package com.epam.interceptor;

import com.epam.constant.ResultEnum;
import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.exception.CustomException;
import com.epam.utils.ContextUtil;
import com.epam.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (token == null) {
            throw new CustomException(ResultEnum.UNAUTHORIZED.getCode(), "未携带token");
        }
        Claims claims = JWTUtil.parseToken(token);
        if (null == claims || claims.isEmpty()) {
            throw new CustomException(ResultEnum.UNAUTHORIZED.getCode(), "invalid token");
        }
        Long id = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);

        User user = userDao.getById(id);
        if (null == user || !username.equals(user.getName())) {
            throw new CustomException(ResultEnum.UNAUTHORIZED.getCode(), "id or username error, please re login");
        }

        ContextUtil.setUserID(id.toString());
        ContextUtil.setUsername(username);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }

}
