package com.dataplatform.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWT 认证过滤器 —— 拦截除登录外的所有请求，验证 Token
 */
@Component
@Order(1)
public class JwtAuthFilter implements Filter {

    // 不需要 Token 的路径（白名单）
    private static final String[] WHITE_LIST = {
            "/api/user/login"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        // 白名单路径直接放行
        for (String white : WHITE_LIST) {
            if (path.startsWith(white)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // 只拦截 /api/ 开头的请求
        if (!path.startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }

        // 获取请求头中的 Token
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            writeError(response, "未登录，请先登录");
            return;
        }

        // 去掉 "Bearer " 前缀（如果有）
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 验证 Token
        try {
            JwtUtil.parse(token);
        } catch (Exception e) {
            writeError(response, "登录已过期，请重新登录");
            return;
        }

        chain.doFilter(request, response);
    }

    private void writeError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(
                "{\"code\":401,\"message\":\"" + message + "\",\"data\":null}"
        );
    }
}
