package com.dataplatform.common;

/**
 * 自定义业务异常 —— 主动抛出的、已知的业务错误
 */
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }

    public int getCode() { return code; }
}
