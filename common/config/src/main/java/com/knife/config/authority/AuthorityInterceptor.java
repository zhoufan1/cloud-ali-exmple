package com.knife.config.authority;

import com.alibaba.fastjson.JSON;
import com.knife.base.utils.JsonUtils;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String UTF8 = StandardCharsets.UTF_8.name();
        request.setCharacterEncoding(UTF8);
        String authorityInfo = request.getHeader(AuthoritySession.AUTHORIZATION_HEADER);
        String authorizationType = AuthoritySession.AUTHORIZATION_TYPE;
        if (!StringUtils.startsWith(authorityInfo, authorizationType)) {
            return true;
        }
        String authority = authorityInfo.substring(authorizationType.length());
        if (StringUtils.isNotBlank(authorityInfo)) {
            authority = authority.trim();
            log.info("get authority data :{}", authority);
            JwtParser jwtParser = Jwts.parser().setSigningKey("knife".getBytes(UTF8));
            String body = JsonUtils.toJSONString(jwtParser.parseClaimsJws(authority).getBody());
            log.info("authority json data:{}", body);
            AuthoritySession authoritySession = JSON.parseObject(body, AuthoritySession.class);
            AuthorityContent.get().setAuthoritySession(authoritySession);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        AuthorityContent.get().clear();
    }
}
