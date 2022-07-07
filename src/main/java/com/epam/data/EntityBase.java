package com.epam.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class EntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String creator;

    @Column
    private String modifier;

    @Column
    @ApiModelProperty("create time")
    private Date gmtCreate;

    @Column
    @ApiModelProperty("modify time")
    private Date gmtModify;
}
