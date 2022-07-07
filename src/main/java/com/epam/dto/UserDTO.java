package com.epam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("用户")
public class UserDTO {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "username不能为空")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("登录接口必传，其他不传，1是保持登录10天，0是默认")
    private Integer keepalive;

}