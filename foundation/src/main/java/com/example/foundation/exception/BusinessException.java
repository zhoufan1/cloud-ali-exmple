package com.example.foundation.exception;

import com.example.foundation.dto.BaseCode;
import com.example.foundation.dto.Response;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private BaseCode code;
    private Throwable cause;

    public BusinessException(BaseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BusinessException(BaseCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
        this.cause = cause;
    }

    public BusinessException(Response response) {
        super(response.getMessage());
        this.code = new BaseCode() {
            @Override
            public String getCode() {
                return response.getCode();
            }

            @Override
            public String getMessage() {
                return response.getMessage();
            }
        };
    }
}
