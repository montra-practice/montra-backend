package com.epam.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "category")
public class Category extends EntityBase implements Serializable {

    @Column(nullable = false)
    public String name;
}
