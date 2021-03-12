package com.knife.authority.security.config;

import com.knife.authority.security.enums.AuthCode;
import com.knife.base.dto.Response;
import com.knife.base.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
@AllArgsConstructor
public class KnifeResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.error("authException : ", authException);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().append(JsonUtils.toJSONString(Response.failed(AuthCode.ACCESS_DENIED)));
    }
}

