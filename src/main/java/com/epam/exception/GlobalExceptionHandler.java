package com.epam.exception;

import com.epam.dto.ResultVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.epam.todoApp.controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResultVO<?> error(HttpServletRequest request, CustomException e) {
        log.error("异常信息：", e);
        return ResultVO.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<?> error(HttpServletRequest request, Exception e) {
        log.error("异常信息：", e);
        return ResultVO.fail("unkown exception");
    }


}
