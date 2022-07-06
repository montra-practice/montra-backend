package com.epam.exception;

import com.epam.constant.ResultEnum;
import com.epam.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice(basePackages = "com.epam.controller")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, CustomException e) {
        log.error("异常信息：", e);
        if (e.getCode() == 0) {
            return Result.fail(e.getMessage());
        }
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * validation参数校验异常 统一处理
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result exceptionHandler500(BindException e) {
        e.printStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : e.getAllErrors()) {
            stringBuilder.append("[");
            stringBuilder.append(((FieldError) error).getField());
            stringBuilder.append(" ");
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append("]");
        }
        return Result.fail(ResultEnum.PARAMETER_VALIDATION_FAILED, "【参数校验失败】 " + stringBuilder.toString());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Result exceptionHandler500(ConstraintViolationException e) {
        e.printStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> error : e.getConstraintViolations()) {
            PathImpl pathImpl = (PathImpl) error.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            stringBuilder.append("[");
            stringBuilder.append(paramName);
            stringBuilder.append(" ");
            stringBuilder.append(error.getMessage());
            stringBuilder.append("]");
        }
        return Result.fail(ResultEnum.PARAMETER_VALIDATION_FAILED, "【参数校验失败】 " + stringBuilder.toString());

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }


}
