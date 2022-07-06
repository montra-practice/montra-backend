package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: test
 * @author: taoz
 * @date: 2022/7/5 14:05
 */
@RestController
@Api(tags = "HelloController")
public class HelloController {

    @ApiOperation(value = "获取当前用户所有的task", notes = "具体描述")
    @GetMapping("/hello")
    public Result hello(UserDTO userDTO) {
        return Result.success("hello,world");
    }

}
