package com.epam.controller;

import com.epam.annotation.RedisLock;
import com.epam.dao.UserRepository;
import com.epam.redis.RedissonUtil;
import com.epam.utils.RedisUtil;
import com.epam.utils.Result;
import com.epam.utils.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: test
 * @author: taoz
 * @date: 2022/7/5 14:05
 */
@RestController
@RequestMapping("test")
@Api(tags = "test")
public class HelloController {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private SpringUtils springUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonUtil redissonUtil;

    @ApiOperation("hello")
    @GetMapping("/hello")
    @RedisLock
    public Result hello() {
        long views = redisUtil.incr("views", 1);
        return Result.success("hello,views:" + views);
    }

}
