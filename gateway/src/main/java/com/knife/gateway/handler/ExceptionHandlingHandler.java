package com.knife.gateway.handler;

import com.knife.base.dto.Response;
import com.knife.base.exception.BusinessException;
import com.knife.base.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @author Knife
 * @date 2020/9/18 11:19
 * @description
 */
@Slf4j
public class ExceptionHandlingHandler extends DefaultErrorWebExceptionHandler {

    public ExceptionHandlingHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                    ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 获取异常转换统一信息
     *
     * @param request
     * @param includeStackTrace
     * @return
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = super.getError(request);
        log.error("exception", error);
        if (error instanceof BusinessException) {
            BusinessException exception = (BusinessException) error;
            Response<Void> commonResult = Response.failed(exception.getCode());
            return JsonUtils.toJSON(commonResult);
        } else {
            Response<Void> commonResult = Response.systemError(error.getMessage());
            return JsonUtils.toJSON(commonResult);
        }
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.valueOf(200).value();
    }
}
