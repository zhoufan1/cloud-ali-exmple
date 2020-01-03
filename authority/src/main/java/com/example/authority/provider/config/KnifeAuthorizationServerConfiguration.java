package com.example.authority.provider.config;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService knifeUserServiceDetailImpl;

    /**
     * 端点安全配置
     *
     * @param security
     */
    @Override
    @SneakyThrows
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .tokenKeyAccess("permitAll()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder);
    }

    /**
     * 客服端配置
     *
     * @param clients
     *
     * @throws Exception
     */
    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory()
                .withClient("client1")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("all")
                .secret(passwordEncoder.encode("123456"));

    }

    /**
     * 授权配置,配置端点，存储方式。
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //允许表单认证
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManagerBean)
                .userDetailsService(knifeUserServiceDetailImpl);
//                .tokenEnhancer(tokenEnhancer);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        ArrayList<TokenEnhancer> tokenEnhancers = Lists.newArrayList(tokenEnhancer, jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
    }


}
