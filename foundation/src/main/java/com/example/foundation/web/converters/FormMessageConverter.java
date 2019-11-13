package com.example.foundation.web.converters;

import com.google.common.collect.Lists;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import java.util.List;

/**
 * @author zhoufan
 * @date 2019/6/4 20:17
 * @description
 */
public final class FormMessageConverter extends FormHttpMessageConverter {
    public final static FormMessageConverter INSTANCE = new FormMessageConverter();

    private FormMessageConverter(){
        setCharset(DEFAULT_CHARSET);
        List<HttpMessageConverter<?>> partConverters = Lists.newArrayList();
        partConverters.add(new ByteArrayHttpMessageConverter());
        partConverters.add(StringMessageConverter.INSTANCE);
        partConverters.add(new SourceHttpMessageConverter());
        partConverters.add(FastJsonMessageConverter.INSTANCE);
        super.setPartConverters(partConverters);
    }



}
