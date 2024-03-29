package com.knife.config.web.handler;

import com.knife.base.dto.Response;
import com.knife.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author knife
 * @date 2019/11/12 14:29
 * @description
 */
@RestControllerAdvice
@Slf4j
@ResponseStatus(HttpStatus.OK)
public class GlobalControllerHandler {
    @ExceptionHandler(Exception.class)
    public Response<Void> exceptionHandle(Exception e) {
        log.error("process system error,message:{} ", e.getMessage());
        return Response.systemError(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Response<Void> exceptionHandle(BusinessException e) {
        log.error("process business error,message:{} ", e.getMessage());
        return Response.failed(e.getCode());
    }

    /**
     * 404异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Response<Void> errorHandler(NoHandlerFoundException e) {
        log.error("not found handle error,message:{} ", e.getMessage());
        return Response.systemError(e.getMessage());
    }

}
