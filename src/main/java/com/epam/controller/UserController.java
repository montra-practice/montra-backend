package com.epam.controller;

import com.epam.annotation.NoRepeatSubmit;
import com.epam.annotation.SysLog;
import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.dto.UserLoginDTO;
import com.epam.dto.UserRegisterDTO;
import com.epam.dto.VerifyDTO;
import com.epam.service.UserService;
import com.epam.utils.EmailUtil;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequestMapping("user")
@Api(tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    private UserRepository userRepository;

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
    @SysLog("用户登录")
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
    public Result register(@RequestBody @Validated UserRegisterDTO dto) {
        return userService.register(dto);
    }

    /**
     * verify
     *
     * @Author taoz
     * @Date 2022/7/13 13:45
     **/
    @PostMapping("/verify")
    @ResponseBody
    @ApiOperation("verify")
    @NoRepeatSubmit
    public Result verify(@RequestBody VerifyDTO verifyDTO) {
        User user = emailUtil.verifyCode(verifyDTO.getVerifyCode());
        if (Objects.nonNull(user)) {
            userRepository.save(user);
            return Result.success();
        }
        return Result.fail("验证失败");
    }

}
