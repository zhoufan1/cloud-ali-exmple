package com.knife.authority.provider.config;

import com.knife.authority.security.constants.SecurityConstants;
import com.knife.config.authority.AuthoritySession;
import com.knife.base.utils.JsonUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Map;


@Configuration
public class KnifeConfig {

    public static class KnifeTokenConfig {
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey("knife");
            return jwtAccessTokenConverter;
        }


        @Bean
        public TokenEnhancer tokenEnhancer() {
            return (accessToken, authentication) -> {
                if (SecurityConstants.CLIENT_CREDENTIALS
                        .equals(authentication.getOAuth2Request().getGrantType())) {
                    return accessToken;
                }
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
                session.setMobile(principal.getUserMob());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(JsonUtils.toJSON(session));
                return accessToken;
            };
        }
    }


    @AllArgsConstructor
    public static class KnifeJdbcService {
        public final DataSource dataSource;

        @Bean
        public JdbcClientDetailsService clientDetails() {
            return new JdbcClientDetailsService(dataSource);
        }
    }
}
