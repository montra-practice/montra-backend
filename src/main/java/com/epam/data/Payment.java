package com.epam.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "payment")
public class Payment extends EntityBase implements Serializable {

    @Column(nullable = false)
    private String name;
}
