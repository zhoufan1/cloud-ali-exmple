package com.example.foundation.web.interceptor;

import com.example.foundation.authority.AuthoritySession;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhouFan
 * @date 2019/11/12 14:33
 * @description
 */
@Slf4j
public class AuthorityRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String authorityHeader = request.getHeader(AuthoritySession.X_AUTHORITY_HEADER);
        if (StringUtils.isNotBlank(authorityHeader)) {
            log.debug("get authorityHeader :{}", authorityHeader);
            template.header(AuthoritySession.X_AUTHORITY_HEADER, authorityHeader);
        }
    }
}
