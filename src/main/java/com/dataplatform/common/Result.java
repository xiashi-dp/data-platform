package com.dataplatform.common;

/**
 * 统一返回格式
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功，带数据
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "成功", data);
    }

    // 成功，无数据（比如删除操作）
    public static <T> Result<T> success() {
        return new Result<>(200, "成功", null);
    }

    // 失败，带错误信息
    public static <T> Result<T> error(String message) {
        return new Result<>(400, message, null);
    }

    // 失败，自定义状态码和错误信息
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
