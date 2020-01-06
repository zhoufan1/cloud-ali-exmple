package com.example.foundation.web.config;

import com.example.foundation.authority.AuthorityInterceptor;
import com.example.foundation.authority.AuthorityResolver;
import com.example.foundation.web.converters.FastJsonMessageConverter;
import com.example.foundation.web.converters.FormMessageConverter;
import com.example.foundation.web.converters.StringMessageConverter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.List;

/**
 * @author zhoufan
 * @date 2019/6/4 20:07
 */
@ConditionalOnWebApplication
@Slf4j
public class WebConfigurationSupport extends WebMvcConfigurationSupport {
    public WebConfigurationSupport() {
        log.info("invoke WebConfigurationSupport");
    }

    private static final List<HttpMessageConverter<?>> CONVERTERS = Lists.newArrayList(
            FastJsonMessageConverter.INSTANCE,
            StringMessageConverter.INSTANCE,
            FormMessageConverter.INSTANCE);

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(CONVERTERS);
        super.addDefaultHttpMessageConverters(converters);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/*");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthorityResolver());
    }

}
