package com.epam.data;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class EntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String creator;

    @Column
    public String modifier;

    @Column
    @ApiModelProperty("create time")
    public Date gmtCreate;

    @Column
    @ApiModelProperty("modify time")
    public Date gmtModify;
}
