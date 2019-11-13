package com.example.foundation.authority;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String UTF8 = IOUtils.UTF8.name();
        request.setCharacterEncoding(UTF8);
        String authorityByte = request.getHeader(AuthoritySession.X_AUTHORITY_HEADER);
        if (StringUtils.isNotBlank(authorityByte)) {
            log.info("get authority data :{}", authorityByte);
            String authority = new String(Base64.getDecoder().decode(authorityByte.getBytes(UTF8)), UTF8);
            log.info("authority json data:{}", authority);
            AuthoritySession authoritySession = JSON.parseObject(authority, AuthoritySession.class);
            AuthorityContent.get().setAuthoritySession(authoritySession);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        AuthorityContent.get().clear();

    }
}
