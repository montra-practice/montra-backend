package com.epam.service;

import com.epam.dto.UserLoginDTO;
import com.epam.dto.UserRegisterDTO;
import com.epam.utils.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    Result getUserInfo(Long id);

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    Result login(UserLoginDTO userLoginDTO);

    /**
     * 用户注册
     *
     * @param userLoginDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    Result register(UserRegisterDTO userLoginDTO);

}
