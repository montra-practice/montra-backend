package com.epam.dto;

import lombok.Data;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Integer status;

    private Long userId;

}
