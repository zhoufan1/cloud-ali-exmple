package com.knife.foundation.provider.cache;

import org.springframework.context.annotation.Bean;

public abstract class CacheLoaderManagerSupport {

    @Bean
    public MapCacheLoader<String, String> stringMapDBCacheLoader(){
        return null;
    }
}
