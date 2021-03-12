package com.knife.authority.security.config;

import com.knife.authority.security.constants.KnifeConstants;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class KnifeSecurityConfig {
    private final ApplicationContext applicationContext;
    private final RedisConnectionFactory redisConnectionFactory;
    private final ResourceServerProperties resourceServerProperties;

    @LoadBalanced
    @Bean
    @Primary
    public RestTemplate authRestTemplate() {
        // 获取上下文配置的ClientHttpRequestInterceptor 实现
        Map<String, ClientHttpRequestInterceptor> beansOfType = applicationContext
                .getBeansOfType(ClientHttpRequestInterceptor.class);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(new ArrayList<>(beansOfType.values()));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public RedisTokenStore authTokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(KnifeConstants.TOKEN_PREFIX);
        return tokenStore;
    }

    @Bean
    @Primary
    public ResourceServerTokenServices resourceServerTokenServices(RestTemplate authRestTemplate) {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        remoteTokenServices.setClientId(resourceServerProperties.getClientId());
        remoteTokenServices.setClientSecret(resourceServerProperties.getClientSecret());
        remoteTokenServices.setRestTemplate(authRestTemplate);
        return remoteTokenServices;
    }
}
