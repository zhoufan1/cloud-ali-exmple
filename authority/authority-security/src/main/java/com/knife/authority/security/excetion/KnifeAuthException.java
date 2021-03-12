package com.knife.authority.security.excetion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.knife.base.dto.BaseCode;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;


@JsonSerialize(using = KnifeAuthExceptionSerializer.class)
@Getter
public class KnifeAuthException extends OAuth2Exception {
    private BaseCode code;
    private Throwable cause;

    public KnifeAuthException(String msg) {
        super(msg);
    }

    public KnifeAuthException(BaseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public KnifeAuthException(BaseCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
        this.cause = cause;
    }
}
