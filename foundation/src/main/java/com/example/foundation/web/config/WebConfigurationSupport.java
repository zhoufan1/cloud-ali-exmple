package com.example.foundation.web.config;

import com.example.foundation.web.converters.FastJsonMessageConverter;
import com.example.foundation.web.converters.FormMessageConverter;
import com.example.foundation.web.converters.StringMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.converter.HttpMessageConverter;
import java.util.List;

/**
 * @author zhoufan
 * @date 2019/6/4 20:07
 */
@ConditionalOnWebApplication
public class WebConfigurationSupport extends HttpMessageConverters {

    private static final List<HttpMessageConverter<?>> CONVERTERS = Lists.newArrayList(
            FastJsonMessageConverter.INSTANCE,
            StringMessageConverter.INSTANCE,
            FormMessageConverter.INSTANCE);

    @Override
    protected List<HttpMessageConverter<?>> postProcessConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(CONVERTERS);
        return super.postProcessConverters(converters);
    }
}
