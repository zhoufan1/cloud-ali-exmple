package com.example.authority.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.oauth2.config.annotation.configurers.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.*;
import org.springframework.security.oauth2.provider.token.store.*;

/**
 * @author ZhouFan
 * @date 2019/9/30 10:57
 * @description
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final String DEMO_RESOURCE_ID = "order";
    /**
     * 端点安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients() //允许表单调用
//                .passwordEncoder(passwordEncoder()) //密编码器
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 客服端配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置两个客户端,一个用于password认证一个用于client认证
     /*   clients.inMemory()
                .withClient("password")
                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "client_credentials")
                .scopes("server")
                .secret("secret")
                .and()
                .withClient("mobile")
                .authorizedGrantTypes("refresh_token", "authorization_code")
                .scopes("app")
                .secret("secret");*/
        clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token","password")
                .scopes("select")
                .authorities("client")
                .secret("123456")
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret("123456");

    }

    /**
     * 授权配置,配置端点，存储方式。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //允许表单认证
        endpoints
                .tokenStore(new InMemoryTokenStore())
                .authenticationManager(authenticationManager);
//                .userDetailsService(userDetailsService);
    }


}
