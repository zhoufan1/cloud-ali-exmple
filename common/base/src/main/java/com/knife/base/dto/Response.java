package com.knife.base.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    private Response() {

    }

    private Response(T data) {
        this.data = data;
    }

    private Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage(), data);
    }

    public static Response<Void> success() {
        return new Response<>(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage());
    }

    public static Response<Void> systemError(String message) {
        return new Response<>(SystemCode.ERROR.getCode(), message);
    }

    public static <T> Response<T> failed(BaseCode baseCode, T data) {
        return new Response<>(baseCode.getCode(), baseCode.getMessage(), data);
    }

    public static Response<Void> failed(BaseCode baseCode) {
        return new Response<>(baseCode.getCode(), baseCode.getMessage());
    }

    public boolean isSuccess() {
        return StringUtils.equals(SystemCode.SUCCESS.getCode(), code);
    }
}
