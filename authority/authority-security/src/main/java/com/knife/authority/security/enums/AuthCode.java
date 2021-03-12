package com.knife.authority.security.enums;

import com.knife.base.dto.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthCode implements BaseCode {
    RE_LOGIN("A0001", "重新登陆"),
    USER_PASS_ERROR("A0002", "用户密码错误"),
    LOCKED("A0003", "用户已被锁定，请联系客服"),
    UN_SUPPORTED_GRANT_TYPE("A0004", "不支持认证类型"),
    ACCESS_DENIED("A0005", "访问被拒绝"),
    ;

    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
