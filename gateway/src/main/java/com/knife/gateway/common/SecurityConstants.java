package com.knife.gateway.common;

/**
 * @author knife
 * @date 2019/11/7 10:07
 * @description
 */
public interface SecurityConstants {
    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * 验证码加密串
     */
    String VERIFY_CODE_ENCRYPT = "verifyCodeEncrypt";
}
