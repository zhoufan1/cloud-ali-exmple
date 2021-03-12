package com.knife.authority.provider.config;

import com.google.common.collect.Lists;
import com.knife.authority.security.config.KnifeClientCredentialsTokenEndpointFilter;
import com.knife.authority.security.config.KnifeResponseExceptionTranslator;
import com.knife.authority.security.constants.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class KnifeAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManagerBean;
    private final TokenEnhancer tokenEnhancer;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final UserDetailsService knifeUserServiceDetailImpl;
    /**
     * 端点安全配置
     *
     * @param security 安全管理
     */
    @Override
    @SneakyThrows
    public void configure(AuthorizationServerSecurityConfigurer security) {
        KnifeClientCredentialsTokenEndpointFilter filter = new KnifeClientCredentialsTokenEndpointFilter(security);
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .addTokenEndpointAuthenticationFilter(filter);

    }

    /**
     * 客服端配置
     *
     * @param clients 客户端配置
     */
    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory()
                .withClient("knife-other")
                .authorizedGrantTypes(SecurityConstants.REFRESH_TOKEN, SecurityConstants.CLIENT_CREDENTIALS)
                .scopes("other")
                .secret("{noop}knife-other")
                .accessTokenValiditySeconds(SecurityConstants.ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(SecurityConstants.REFRESH_TOKEN_VALIDITY_SECONDS)
                .and()
                .withClient("knife-app")
                .authorizedGrantTypes(SecurityConstants.REFRESH_TOKEN, SecurityConstants.PASSWORD)
                .scopes("app")
                .secret("{noop}knife-app")
                .accessTokenValiditySeconds(SecurityConstants.ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(SecurityConstants.REFRESH_TOKEN_VALIDITY_SECONDS)
                .and()
                .withClient("knife-web")
                .authorizedGrantTypes(SecurityConstants.REFRESH_TOKEN, SecurityConstants.PASSWORD)
                .scopes("web")
                .secret("{noop}knife-web")
                .accessTokenValiditySeconds(SecurityConstants.ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(SecurityConstants.REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    /**
     * 授权配置,配置端点，存储方式。
     *
     * @param endpoints 配置端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //允许表单认证
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManagerBean)
                .userDetailsService(knifeUserServiceDetailImpl);
        // 增強支持jwt参数
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        ArrayList<TokenEnhancer> tokenEnhancers = Lists.newArrayList(tokenEnhancer);
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        endpoints.exceptionTranslator(new KnifeResponseExceptionTranslator());
    }

}
