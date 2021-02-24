package com.knife.foundation.web.handler;

import com.knife.foundation.dto.Response;
import com.knife.foundation.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author knife
 * @date 2019/11/12 14:29
 * @description
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalControllerHandler{
    @ExceptionHandler(Exception.class)
    public Response exceptionHandle(Exception e) {
        log.error("process system error,message:{} ", e.getMessage());
        return Response.systemError(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Response exceptionHandle(BusinessException e) {
        log.error("process business error,message:{} ", e.getMessage());
        return Response.failed(e.getCode());
    }

}
