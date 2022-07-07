package com.epam.controller;

import com.epam.dao.UserRepository;
import com.epam.data.User;
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

    @ApiOperation("hello")
    @GetMapping("/hello")
    public Result hello() {
        User user = userDao.getById(1L);
        return Result.success("hello,world");
    }

}
