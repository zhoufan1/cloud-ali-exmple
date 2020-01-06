package com.example.authority.dto;

import com.example.foundation.dto.BaseCode;
import lombok.AllArgsConstructor;

/**
 * @author knife
 * @date 2019/11/13 14:52
 * @description
 */
@AllArgsConstructor
public enum BusinessCode implements BaseCode {

    UnAuthorization("A0001", "授权失败");;


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
