package com.epam.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "bill")
public class Bill extends EntityBase implements Serializable {

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long paymentId;

    @Column(nullable = false)
    private Long userId;

    @Column(name="`repeat`")
    private Boolean repeat;

    @Column
    private String frequency;

    @Column
    private String endAfter;

}
