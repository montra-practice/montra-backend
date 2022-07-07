package com.epam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.List;

@Data
public class BillDto {

    private Long categoryId;

    private Long paymentId;

    private Boolean repeat;

    private String description;

    private String frequency;

    private String endAfter;

    @ApiModelProperty(value = "after uploading attachment, put returned id here")
    private List<Long> attachments;
}
