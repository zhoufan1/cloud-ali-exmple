package com.knife.authority.provider.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.knife.authority.enums.BusinessCode;
import com.knife.base.dto.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author knife
 * @date 2019/11/13 14:46
 * @description
 */
@Slf4j
@Component
public class KnifeAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) {
        log.warn("授权失败，禁止访问 {} , exception:{}", request.getRequestURI(), authException);
        response.setCharacterEncoding(IOUtils.UTF8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        writer.append(JSON.toJSONString(Response.failed(BusinessCode.UnAuthorization)));
    }
}
