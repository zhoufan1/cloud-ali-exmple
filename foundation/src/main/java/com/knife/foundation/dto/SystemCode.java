package com.knife.foundation.dto;

public enum  SystemCode implements BaseCode {
    SUCCESS("200", "SUCCESS"),
    ERROR("500", "ERROR"),
    ;


    SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

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
