package com.knife.authority.provider.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


/**
 * 登录事件
 */
@Slf4j
@Component
public class KnifeAuthenticationEnvent implements ApplicationListener<AbstractAuthenticationEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        log.info("onApplicationEvent  start ...");
        Authentication authentication = event.getAuthentication();
        if (event instanceof AuthenticationSuccessEvent) {
            log.info("{} authentication success ...", authentication.getPrincipal());
        }

        if (event instanceof AbstractAuthenticationFailureEvent) {
            AuthenticationException exception = ((AbstractAuthenticationFailureEvent) event).getException();
            log.error("authentication failure , 异常：{}", exception);
        }
    }
}
