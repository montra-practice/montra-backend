package com.epam.controller;

import com.epam.annotation.NoRepeatSubmit;
import com.epam.dto.UserLoginDTO;
import com.epam.dto.UserRegisterDTO;
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
@Api(tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * getUser
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
     * login
     *
     * @param dto
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("login")
    @NoRepeatSubmit
    public Result login(@RequestBody @Validated UserLoginDTO dto) {
        return userService.login(dto);
    }

    /**
     * register
     *
     * @param dto
     * @return
     * @Author taoz
     * @Date 2022/7/6 11:13
     **/
    @PostMapping("/register")
    @ResponseBody
    @ApiOperation("register")
    @NoRepeatSubmit
    public Result register(@RequestBody UserRegisterDTO dto) {
        return userService.register(dto);
    }

}
