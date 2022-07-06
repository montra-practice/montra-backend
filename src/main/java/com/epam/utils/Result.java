package com.epam.utils;

import com.epam.constant.ResultEnum;
import lombok.Data;

/**
 * @description: 返回结果
 * @author: taoz
 * @date: 2022/7/5 15:29
 */
@Data
public class Result<T> {
    /**
     * 状态码
     * 成功：200
     * 失败：其他
     */
    private int code;

    /**
     * 失败状态码描述
     * 如果成功不返回
     * 失败返回状态码对应的msg消息
     */
    private String msg;

    /**
     * 请求数据的结果
     */
    private T data;

    public Result(int code) {
        this.setCode(code);
    }

    public Result(int code, T data) {
        this.setCode(code);
        this.setData(data);
    }

    public Result(int code, String msg) {
        this.setCode(code);
        this.setData(data);
    }

    public static <T> Result<T> success() {
        return new Result<T>(200);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(200, data);
    }


    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return new Result<T>(resultEnum.getCode());
    }

    public static <T> Result<T> fail(ResultEnum resultEnum, String msg) {
        return new Result<T>(resultEnum.getCode(), msg);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(ResultEnum.FAIL.getCode(), msg);
    }

}
