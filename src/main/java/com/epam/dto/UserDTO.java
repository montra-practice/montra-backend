package com.epam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("用户")
public class UserDTO {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "username不能为空")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    @Email(message = "username不能为空")
    private String email;

}
