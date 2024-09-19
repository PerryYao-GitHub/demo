package com.ypy.backend.common.exception;

import com.ypy.backend.common.Code;

/**
 * 自定义异常
 */
public class BusinessException extends RuntimeException {
    private final Code code;
    private final String description;

    public BusinessException(Code code, String description) {
        this.code = code;
        this.description = description;
    }

    public BusinessException(Code code) {
        this.code = code;
        this.description = "";
    }

    public Code getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
