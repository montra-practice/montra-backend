package com.epam.service.impl;

import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.dto.UserLoginDTO;
import com.epam.dto.UserRegisterDTO;
import com.epam.exception.CustomException;
import com.epam.service.UserService;
import com.epam.utils.JWTUtil;
import com.epam.utils.MD5Utils;
import com.epam.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    @Override
    public Result getUserInfo(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        User userInfo = user.get();
        return Result.success(user);
    }

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        if (StringUtils.isAnyEmpty(userLoginDTO.getUsername(), userLoginDTO.getPassword())) {
            throw new CustomException("username password null");
        }
        User user = userRepository.findOneByName(userLoginDTO.getUsername());
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
        if (StringUtils.isAnyEmpty(userRegisterDTO.getUsername(), userRegisterDTO.getPassword())) {
            throw new CustomException("username password null");
        }
        User user = userRepository.findOneByName(userRegisterDTO.getUsername());
        if (null != user) {
            throw new CustomException("user exist");
        }
        User userTosave = new User();
        userTosave.setName(userRegisterDTO.getUsername());
        userTosave.setPassword(MD5Utils.inputPassToFormPass(userRegisterDTO.getPassword()));
        userRepository.save(userTosave);
        return Result.success();
    }
}
