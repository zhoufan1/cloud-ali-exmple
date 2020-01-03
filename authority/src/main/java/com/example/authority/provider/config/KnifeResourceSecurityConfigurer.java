package com.example.authority.provider.config;

import com.example.authority.provider.handler.KnifeAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import java.util.List;

/**
 * 保护所需要访问的资源
 */
@EnableResourceServer
@Configuration
@AllArgsConstructor
public class KnifeResourceSecurityConfigurer extends ResourceServerConfigurerAdapter {

    private final KnifeFilterIgnoreConfig knifeFilterIgnoreConfig;
    private final KnifeAccessDeniedHandler knifeAccessDeniedHandler;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.accessDeniedHandler(knifeAccessDeniedHandler);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry register =
                http.authorizeRequests();
        List<String> urls = knifeFilterIgnoreConfig.getUrls();
        urls.forEach(url -> register.antMatchers(url).permitAll());
        register.anyRequest().authenticated().and().csrf().disable();
    }
}
