package com.knife.foundation.web.converters;

import com.alibaba.fastjson.util.IOUtils;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * @author knife
 * @date 2019/6/4 20:20
 * @description
 */
public final class StringMessageConverter extends StringHttpMessageConverter {
    public static final StringMessageConverter INSTANCE = new StringMessageConverter();

    private StringMessageConverter(){
        super(IOUtils.UTF8);
    }
}
