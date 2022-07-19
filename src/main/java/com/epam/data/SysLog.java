package com.epam.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 日志实体类
 * @author: taoz
 * @date: 2022/7/13 17:35
 */
@Data
@Entity
@Table(name = "sys_log")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //用户名
    @Column
    private String userName;

    //用户操作
    @Column
    private String operation;

    //请求方法
    @Column
    private String method;

    //请求参数
    @Column
    private String params;

    //执行时常
    @Column
    private Long operateTime;

    //ip地址
    @Column
    private String ipAddress;

    //创建时间
    @Column
    private Date createDate;

    //日志类别
    @Column
    private String logType;

    //状态
    @Column
    private int status;

}
