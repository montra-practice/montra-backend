package com.epam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("用户登录入参")
public class UserLoginDTO {

    @ApiModelProperty(value = "用户邮箱", required = true)
    @Email(message = "邮箱格式不正确")
    @NotEmpty(message = "email不能为空")
    private String email;

    @ApiModelProperty(value = "用户密码", required = true)
    @NotEmpty(message = "password不能为空")
    private String password;

}
