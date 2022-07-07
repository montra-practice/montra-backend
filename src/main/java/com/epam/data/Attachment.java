package com.epam.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "attachment")
public class Attachment extends EntityBase implements Serializable {

    @Column
    private String name;

    @Column
    private String type;

    @Column(length = 1024 * 5)
    private byte[] attachByte;
}
