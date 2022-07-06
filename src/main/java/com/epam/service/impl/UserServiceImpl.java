package com.epam.service.impl;

import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.dto.UserDTO;
import com.epam.exception.CustomException;
import com.epam.service.UserService;
import com.epam.utils.JWTUtil;
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
     * @param userDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    @Override
    public Result login(UserDTO userDTO) {
        if (StringUtils.isAnyEmpty(userDTO.getUsername(), userDTO.getPassword())) {
            throw new CustomException("username password null");
        }
        User user = userRepository.findOneByName(userDTO.getUsername());
        if (null == user) {
            throw new CustomException("username password error");
        }
        if (userDTO.getPassword().equals(user.getPassword())) {
            String token = JWTUtil.createToken(user.getId(), user.getName());
            return Result.success(token);
        }
        return Result.success();
    }

    /**
     * 用户注册
     *
     * @param userDTO
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:14
     **/
    @Override
    public Result register(UserDTO userDTO) {
        if (StringUtils.isAnyEmpty(userDTO.getUsername(), userDTO.getPassword())) {
            throw new CustomException("username password null");
        }
        User user = userRepository.findOneByName(userDTO.getUsername());
        if (null != user) {
            throw new CustomException("user exist");
        }
        User userTosave = new User();
        userTosave.setName(userDTO.getUsername());
        userTosave.setPassword(userDTO.getPassword());
        userRepository.save(userTosave);
        return Result.success();
    }
}
