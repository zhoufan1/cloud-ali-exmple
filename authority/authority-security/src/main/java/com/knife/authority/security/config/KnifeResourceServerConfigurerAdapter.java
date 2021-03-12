package com.knife.authority.security.config;

import com.alibaba.fastjson.JSON;
import com.knife.base.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.List;

@Configuration
@AllArgsConstructor
@Slf4j
public class KnifeResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    private final KnifeClientIgnore knifeClientIgnore;
    private final KnifeResourceAuthExceptionEntryPoint knifeResourceAuthExceptionEntryPoint;
    private final RedisTokenStore authTokenStore;
    private final ResourceServerTokenServices resourceServerTokenServices;
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
        resources.tokenServices(resourceServerTokenServices);
        resources.tokenStore(authTokenStore);
        resources.authenticationEntryPoint(knifeResourceAuthExceptionEntryPoint);
    }
}
