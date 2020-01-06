package com.example.foundation.authority;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String UTF8 = IOUtils.UTF8.name();
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
            JwtParser jwtParser = Jwts.parser().setSigningKey("knife".getBytes(IOUtils.UTF8.name()));
            String body = JSON.toJSONString(jwtParser.parseClaimsJws(authority).getBody());
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
