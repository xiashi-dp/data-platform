package com.dataplatform.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理 —— 所有 Controller 抛出的异常统一在这里处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理业务异常（主动抛出的），返回自定义 code 和消息
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理其他所有未预期的异常，不暴露具体信息给前端
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 生产环境这里可以加日志记录，暂时先打印
        e.printStackTrace();
        return Result.error(500, "服务器内部错误");
    }
}
