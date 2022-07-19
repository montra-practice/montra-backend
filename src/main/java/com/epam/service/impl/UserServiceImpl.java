package com.epam.service.impl;

import com.epam.annotation.RedisLock;
import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.dto.UserLoginDTO;
import com.epam.dto.UserRegisterDTO;
import com.epam.exception.CustomException;
import com.epam.service.UserService;
import com.epam.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    @RedisLock
    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findOneByEmail(userLoginDTO.getEmail());
        if (null == user) {
            throw new CustomException("username password error");
        }
        if (MD5Utils.inputPassToFormPass(userLoginDTO.getPassword()).equals(user.getPassword())) {
            String token = JWTUtil.createToken(user.getId(), user.getName());
            return Result.success(token);
        } else {
            throw new CustomException("username password error");
        }
    }

    /**
     * 用户注册
     *
     * @param userRegisterDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    @Override
    public Result register(UserRegisterDTO userRegisterDTO) {
        User user = userRepository.findOneByEmail(userRegisterDTO.getEmail());
        if (null != user) {
            throw new CustomException("user exist");
        }
        String code = emailUtil.sendMail(userRegisterDTO.getEmail());
        User userTosave = new User();
        userTosave.setName(userRegisterDTO.getUsername());
        userTosave.setPassword(MD5Utils.inputPassToFormPass(userRegisterDTO.getPassword()));
        userTosave.setEmail(userRegisterDTO.getEmail());
        redisUtil.set(code, userTosave, 300);
        return Result.success();
    }
}
