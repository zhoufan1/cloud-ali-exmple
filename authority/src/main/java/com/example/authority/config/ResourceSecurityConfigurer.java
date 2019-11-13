package com.example.authority.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.http.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.*;

/**
 * @author ZhouFan
 * @date 2019/10/25 11:51
 * @description
 */
@EnableResourceServer
@Configuration
public class ResourceSecurityConfigurer extends ResourceServerConfigurerAdapter{
    private static final String DEMO_RESOURCE_ID = "order";
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/com.example.user/**").authenticated();
    }
}
