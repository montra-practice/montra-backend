package com.epam.controller;

import com.epam.dao.UserRepository;
import com.epam.data.User;
import com.epam.redis.RedissonUtil;
import com.epam.utils.EmailUtil;
import com.epam.utils.RedisUtil;
import com.epam.utils.Result;
import com.epam.utils.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("hello")
    @GetMapping("/hello")
    public Result hello() throws MessagingException {
        User user = new User();
        user.setName("taozhi");
        redisUtil.set("key", user);
        User value = (User) redisUtil.get("key");
        return Result.success(value);
    }

}
