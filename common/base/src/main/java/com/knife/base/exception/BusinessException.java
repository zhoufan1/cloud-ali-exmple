package com.knife.base.exception;

import com.knife.base.dto.BaseCode;
import com.knife.base.dto.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
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
