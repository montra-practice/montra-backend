package com.epam.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "bill")
public class Bill extends EntityBase implements Serializable {

    @Column(nullable = false)
    public Long categoryId;

    @Column(nullable = false)
    public Long paymentId;

    @Column(name="`repeat`")
    public Boolean repeat;

    @Column
    public String frequency;

    @Column
    public String endAfter;

}
