package com.epam.controller;

import com.epam.annotation.APILimit;
import com.epam.annotation.SysLog;
import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.redis.RedissonUtil;
import com.epam.utils.EmailUtil;
import com.epam.utils.RedisUtil;
import com.epam.utils.Result;
import com.epam.utils.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * @description: test
 * @author: taoz
 * @date: 2022/7/5 14:05
 */
@RestController
@RequestMapping("test")
@Api(tags = "test")
@Validated
public class HelloController {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private SpringUtils springUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonUtil redissonUtil;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation("hello")
    @GetMapping("/hello")
    @SysLog("hello")
    @APILimit
    public Result hello() throws MessagingException {
        User user = new User();
        user.setName("djskf");
        redisUtil.set("user", user);
        return Result.success(redisUtil.get("user"));
    }

}
