package com.epam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description:
 * @author: taoz
 * @date: 2022/7/13 16:09
 */
@Data
@ApiModel("验证码入参")
public class VerifyDTO {
    @ApiModelProperty(value = "验证码", required = true)
    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;
}
