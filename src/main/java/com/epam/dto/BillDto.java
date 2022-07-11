package com.epam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BillDto {

    @NotNull(message = "is required")
    private Long categoryId;

    @NotNull(message = "is required")
    private Long paymentId;

    private Boolean repeat;

    private String description;

    private String frequency;

    private String endAfter;

    @ApiModelProperty(value = "after uploading attachment, put returned id here")
    private List<Long> attachments;
}
