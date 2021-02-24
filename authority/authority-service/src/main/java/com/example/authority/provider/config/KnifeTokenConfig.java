package com.example.authority.provider.config;

import com.example.foundation.authority.AuthoritySession;
import com.example.foundation.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
public class KnifeTokenConfig {
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("knife");
        return jwtAccessTokenConverter;
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            UserInfo principal = (UserInfo) authentication.getPrincipal();
            AuthoritySession session = new AuthoritySession();
            OAuth2Request oAuth2Request = authentication.getOAuth2Request();
            session.setVersion("1.0");
            session.setId(principal.getId());
            String clientTime = oAuth2Request.getRequestParameters().get("clientTime");
            session.setClientTime(StringUtils.isNotBlank(clientTime) ?
                    NumberUtils.toLong(clientTime) : System.currentTimeMillis());
            session.setUserName(principal.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(JsonUtils.toJSON(session));
            return accessToken;
        };
    }
}
