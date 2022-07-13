package com.epam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @description: 用户注册入参
 * @author: taoz
 * @date: 2022/7/7 11:24
 */
@Data
@ApiModel("用户注册入参")
public class UserRegisterDTO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "username不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @ApiModelProperty(value = "用户邮箱", required = true)
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "验证码", required = true)
    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;

}
