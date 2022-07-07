package com.epam.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.List;

@Data
public class BillDto {

    private Long categoryId;

    private Long paymentId;

    private Boolean repeat;

    private String frequency;

    private String endAfter;

    private List<Long> attachments;
}