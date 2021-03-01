package com.knife.authority.provider.config;

import com.google.common.collect.Lists;
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

import java.util.ArrayList;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class KnifeAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManagerBean;
    private final TokenEnhancer tokenEnhancer;
    private final TokenStore tokenStore;
    //    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final UserDetailsService knifeUserServiceDetailImpl;

    /**
     * 端点安全配置
     *
     * @param security 安全管理
     */
    @Override
    @SneakyThrows
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
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
                .withClient("knife-app")
                .authorizedGrantTypes("refresh_token", "client_credentials")
                .scopes("app")
                .secret("{bcrypt}$2a$10$4xuS09PXFZC0SHpWWsiZpO2obydzi6wWwdIPfIxc1gwIwst7iq17C")
                .accessTokenValiditySeconds(60 * 60 * 5)
                .refreshTokenValiditySeconds(60 * 60 * 6)
                .and()
                .withClient("knife-web")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("web")
                .secret("{bcrypt}$2a$10$4xuS09PXFZC0SHpWWsiZpO2obydzi6wWwdIPfIxc1gwIwst7iq17C")
                .accessTokenValiditySeconds(60 * 60 * 3)
                .refreshTokenValiditySeconds(60 * 60 * 5);
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
//        endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
    }

}
