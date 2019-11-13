package com.example.gateway.common;

import com.example.foundation.dto.BaseCode;
import lombok.AllArgsConstructor;

/**
 * @author ZhouFan
 * @date 2019/11/7 10:14
 * @description
 */
@AllArgsConstructor
public enum  GateWayCode implements BaseCode {
    CaptchaError("G0001","验证码错误"),
    CaptchaEncryptNull("G0002","验证码密文为空"),
    CaptchaPast("G0003","验证码已过期")
    ;

    
    
    private String code;
    private String message;
    
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
