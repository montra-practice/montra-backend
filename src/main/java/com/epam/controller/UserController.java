package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    @GetMapping("/{id}")
    @ResponseBody
    public Result getUser(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    /**
     * 获取用户信息
     *
     * @param dto
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("用户登录")
    public Result login(@RequestBody @Validated UserDTO dto) {
        return userService.login(dto);
    }

    /**
     * 获取用户信息
     *
     * @param dto
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    @GetMapping("/register")
    @ResponseBody
    @ApiOperation("用户注册")
    public Result register(@RequestBody UserDTO dto) {
        return userService.register(dto);
    }

}
