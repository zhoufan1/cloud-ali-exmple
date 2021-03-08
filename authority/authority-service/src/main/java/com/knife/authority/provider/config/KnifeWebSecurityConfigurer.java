package com.knife.authority.provider.config;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@EnableWebSecurity()
@AllArgsConstructor
@Slf4j
public class KnifeWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final KnifeFilterIgnoreConfig knifeFilterIgnoreConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry register =
                http.authorizeRequests();
        List<String> urls = knifeFilterIgnoreConfig.getUrls();
        urls.forEach(url -> register.antMatchers(url).permitAll());
        register.anyRequest().authenticated().and().csrf().disable();
        log.info("ignore urls :{}", JSON.toJSONString(knifeFilterIgnoreConfig.getUrls()));
    }
}
