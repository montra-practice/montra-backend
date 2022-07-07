package com.epam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("用户")
public class UserLoginDTO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "username不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

}
