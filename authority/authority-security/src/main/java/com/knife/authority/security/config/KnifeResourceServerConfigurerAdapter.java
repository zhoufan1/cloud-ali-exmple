package com.knife.authority.security.config;

import com.alibaba.fastjson.JSON;
import com.knife.foundation.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@AllArgsConstructor
@Slf4j
public class KnifeResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    private final KnifeClientIgnore knifeClientIgnore;
    private final RemoteTokenServices remoteTokenServices;
    private final KnifeResourceAuthExceptionEntryPoint knifeResourceAuthExceptionEntryPoint;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        List<String> urls = knifeClientIgnore.getUrls();
        if (!CollectionUtils.isNullOrEmpty(urls)) {
            log.info("ignore url :{}", JSON.toJSONString(urls));
            urls.forEach(url -> registry.antMatchers(url).permitAll());
        }
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        remoteTokenServices.setRestTemplate(restTemplate());
        resources.tokenServices(remoteTokenServices);
        resources.authenticationEntryPoint(knifeResourceAuthExceptionEntryPoint);
    }
}
