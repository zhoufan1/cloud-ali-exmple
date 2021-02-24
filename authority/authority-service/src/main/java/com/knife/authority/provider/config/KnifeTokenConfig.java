package com.knife.authority.provider.config;

import com.knife.authority.constants.KnifeConstants;
import com.knife.foundation.authority.AuthoritySession;
import com.knife.foundation.utils.JsonUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Map;


@Configuration
@AllArgsConstructor
public class KnifeTokenConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    /*@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("knife");
        return jwtAccessTokenConverter;
    }*/
    @Bean
    public RedisTokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(KnifeConstants.TOKEN_PREFIX);
        return tokenStore;
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            UserInfo principal = (UserInfo) authentication.getPrincipal();
            AuthoritySession session = new AuthoritySession();
            OAuth2Request oAuth2Request = authentication.getOAuth2Request();
            session.setVersion("1.0");
            session.setId(principal.getId());
            Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
            String clientTime = requestParameters.get("clientTime");
            session.setClientTime(StringUtils.isNotBlank(clientTime) ?
                    NumberUtils.toLong(clientTime) : System.currentTimeMillis());
            session.setUserName(principal.getUsername());
            session.setMobile(requestParameters.get("mobile"));
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(JsonUtils.toJSON(session));
            return accessToken;
        };
    }
}
