package com.knife.authority.security.constants;

public interface SecurityConstants {
    String CLIENT_CREDENTIALS = "client_credentials";
    String REFRESH_TOKEN = "refresh_token";
    String PASSWORD = "password";
    // 一天有效访问
    int ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24;
    // 三天内有效刷新
    int REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 3;



}
