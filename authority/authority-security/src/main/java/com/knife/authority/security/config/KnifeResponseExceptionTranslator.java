package com.knife.authority.security.config;

import com.knife.authority.security.enums.AuthCode;
import com.knife.authority.security.excetion.KnifeAuthException;
import com.knife.base.dto.BaseCode;
import com.knife.base.dto.SystemCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Slf4j
public class KnifeResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<KnifeAuthException> translate(Exception e) {
        log.error("oauth security error ", e);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
        headers.set(HttpHeaders.PRAGMA, "no-cache");
        BaseCode code;
        if (e instanceof UnsupportedGrantTypeException) {
            code = AuthCode.UN_SUPPORTED_GRANT_TYPE;
        } else if (e instanceof InvalidTokenException) {
            code = AuthCode.USER_PASS_ERROR;
        } else {
            code = SystemCode.ERROR;
        }
        return ResponseEntity.ok().headers(headers).body(new KnifeAuthException(code));
    }
}
