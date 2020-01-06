package com.example.gateway.config;

import com.example.gateway.handler.HystrixFallbackHandler;
import com.example.gateway.handler.ImageCodeHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 *
 *
 * @author knife
 * @date 2019/10/14 17:42
 * @description 设置断路器与验证的路由信息
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class RouterFunctionConfiguration {
    private final HystrixFallbackHandler hystrixFallbackHandler;
    private final ImageCodeHandler imageCodeHandler;

    @Bean
    public RouterFunction routerFunction() {
        final String CODE_PATH = "/code";
        final String FALL_BACK_PATH = "/fallback";
        return RouterFunctions.route(RequestPredicates.path(FALL_BACK_PATH)
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET(CODE_PATH).
                        and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler);
    }


}
